<template>
    <v-container>
      <v-row>
        <v-col>
          <v-btn color="primary" @click="openDialog">Add Tag</v-btn>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <v-data-table :headers="headers" :items="tags" item-key="id" class="elevation-1">
            <template v-slot:item.actions="{ item }">
              <v-icon small class="mr-4" @click="editTag(item)">mdi-pencil</v-icon>
              <v-icon small @click="deleteTag(item)">mdi-delete</v-icon>
            </template>
          </v-data-table>
        </v-col>
      </v-row>
      <tag-dialog :tag="selectedTag" :dialogVisible="dialogVisible" @update:dialogVisible="dialogVisible = $event" @refresh="fetchTags" />
    </v-container>
  </template>
  
  <script setup lang="ts">
  import { ref, onMounted } from 'vue';
  import tagService from '@/services/tagService';
  import TagDialog from '@/components/TagDialog.vue';
  import router from '@/router';
  
  const tags = ref([]);
  const selectedTag= ref(null);
  const dialogVisible = ref(false);
  
  const headers = [
    { title: 'Name', value: 'name' },
    { title: 'Actions', value: 'actions', sortable: false }
  ];
  
  const dialog = ref(false);
  console.log(headers);
  
  const fetchTags = async () => {
    const response = await tagService.getAllTags();
    console.log(response.data);
    tags.value = response.data;
  };
  
  const openDialog = () => {
    selectedTag.value = null;
    dialogVisible.value = true;
  };
  
  const editTag= (tag: any) => {
    selectedTag.value = tag;
    dialogVisible.value = true;
    console.log(selectedTag.value);
  };
  
  const deleteTag = async (tag: { id: number }) => {
    await tagService.deleteTag(tag.id);
    fetchTags();
    console.log('Tag deleted');
  };
  
//   const viewCustomer = (id: number) => {
//     router.push(`/customers/${id}`);
//   };
  
  onMounted(fetchTags);
  </script>
  