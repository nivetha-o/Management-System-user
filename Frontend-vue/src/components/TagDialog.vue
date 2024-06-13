<template>
    <v-dialog v-model="dialog" max-width="600px">
      <v-card>
        <v-card-title>
          <span class="headline">{{ isEdit ? 'Edit' : 'Add' }} Tag</span>
        </v-card-title>
        <v-card-text>
          <v-form ref="form" v-model="valid">
            <v-text-field
              v-model="localTag.name"
              label="Name"
              :rules="[rules.required]"
              required
            ></v-text-field>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" @click="close">Cancel</v-btn>
          <v-btn color="blue darken-1" @click="saveTag">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </template>
  
  <script setup lang="ts">
  import { ref, watch, defineProps, defineEmits } from 'vue';
  import tagService from '@/services/tagService';
  
  // Define the Tag interface
  interface Tag {
    id?: number;
    name: string;
  }
  // Define component props
  const props = defineProps<{
    tag: Tag | null;
    dialogVisible: boolean;
  }>();
  
  // Define component emits
  const emit = defineEmits(['refresh', 'update:dialogVisible']);
  
  // State variables
  const dialog = ref(false);
  const valid = ref(false);
  const localTag= ref<Tag>({
    name: ''
  });
  const isEdit = ref(false);
  
  // Form validation rules
  const rules = {
    required: (value: string) => !!value || 'Required.'
  };
  
  console.log("dialog",dialog.value);
  console.log("edit",isEdit.value);

  
  watch(
    () => props.tag,
    (newVal) => {
      if (newVal !== null && newVal !== undefined) {
        localTag.value = { ...newVal };
        isEdit.value = true;
      } else {
        // Adding new tag
        localTag.value = { name: ''};
        isEdit.value = false;
      }
    },
    { immediate: true }
  );


watch(
  () => props.dialogVisible,
  (newVal) => {
    dialog.value = newVal;
  },
  { immediate: true }
);
  
  // Close dialog function
  const close = () => {
    dialog.value = false;
    emit('update:dialogVisible', false);
  };
  
  // Save tag function
  const saveTag = async () => {
    if (valid.value) {
      if (isEdit.value && props.tag) {
        await tagService.updateTag(props.tag.id!, localTag.value);
      } else {
        await tagService.creatTag(localTag.value);
      }
      close();
      emit('refresh');
      console.log('Tag saved');
    }
  };
  </script>
  