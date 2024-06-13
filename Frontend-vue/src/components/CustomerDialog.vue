<template>
  <v-dialog v-model="dialog" max-width="600px">
    <v-card>
      <v-card-title>
        <span class="headline">{{ isEdit ? 'Edit' : 'Add' }} User</span>
      </v-card-title>
      <v-card-text>
        <v-form ref="form" v-model="valid">
          <v-text-field
            v-model="localCustomer.name"
            label="Name"
            :rules="[rules.required, rules.maxLength]"
            required
          ></v-text-field>
          <v-text-field
            v-model="localCustomer.email"
            :rules="[rules.required, rules.email]"
            label="Email"
            required
          ></v-text-field>
          <v-text-field
            v-model="localCustomer.phNo"
            :rules="[rules.required, rules.phone]"
            label="Phone Number"
          ></v-text-field>
          <v-combobox
            v-model="localCustomer.tags"
            label="Tags"
            multiple
            chips
            clearable
          ></v-combobox>
        </v-form>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="blue darken-1" @click="close">Cancel</v-btn>
        <v-btn color="blue darken-1" @click="saveCustomer">Save</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script setup lang="ts">
import { ref, watch, defineProps, defineEmits } from 'vue';
import customerService from '@/services/customerService';

// Define the Customer interface
interface Customer {
  id?: number;
  name: string;
  email: string;
  phNo: string;
  tags: string[];
}

// Define component props
const props = defineProps<{
  customer: Customer | null;
  dialogVisible: boolean;
}>();

// Define component emits
const emit = defineEmits(['refresh', 'update:dialogVisible']);

// State variables
const dialog = ref(false);
const valid = ref(false);
const localCustomer = ref<Customer>({ name: '', email: '', phNo: '', tags: [] });
const isEdit = ref(false);

// Form validation rules
const rules = {
  required: (value: string) => !!value || 'Required.',
  maxLength: (value: string) => value.length <= 20 || 'Max 20 characters',
  email: (value: string) => /.+@.+\..+/.test(value) || 'E-mail must be valid.',
  phone: (value: string) => /^\d{10}$/.test(value) || 'Phone number must be 10 digits.'
};

watch(
  () => props.customer,
  (newVal) => {
    if (newVal !== null && newVal !== undefined) {
      localCustomer.value = { ...newVal };
      isEdit.value = true;
    } else {
      // Adding new customer
      localCustomer.value = { name: '', email: '', phNo: '', tags: [] };
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

// Save customer function
const saveCustomer = async () => {
  if (valid.value) {
    if (isEdit.value && props.customer) {
      await customerService.updateCustomer(props.customer.id!, localCustomer.value);
      console.log('Customer Updated');
    } else {
      await customerService.createCustomer(localCustomer.value);
      console.log('Customer saved');
      localCustomer.value = { name: '', email: '', phNo: '', tags: [] };
    }
    close();
    emit('refresh');
    // console.log('Customer saved');
  }
};
</script>
