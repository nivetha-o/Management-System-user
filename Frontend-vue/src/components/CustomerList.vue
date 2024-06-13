<template>
  <v-container>
    <v-row>
      <v-col>
        <v-btn color="primary" @click="openDialog">Add User</v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-data-table :headers="headers" :items="customers" item-key="id" class="elevation-1">
          <template v-slot:item.actions="{ item }">
            <v-icon small class="mr-4" @click="editCustomer(item)">mdi-pencil</v-icon>
            <v-icon small @click="deleteCustomer(item)">mdi-delete</v-icon>
          </template>
          <template v-slot:item.tags="{ item }">
            <v-chip v-for="(tag, index) in item.tags" :key="index" class="mr-2" outlined>
              {{ tag }}
            </v-chip>
          </template>
        </v-data-table>
      </v-col>
    </v-row>
    <customer-dialog :customer="selectedCustomer" :dialogVisible="dialogVisible" @update:dialogVisible="dialogVisible = $event" @refresh="fetchCustomers" />
  </v-container>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import customerService from '@/services/customerService';
import CustomerDialog from '@/components/CustomerDialog.vue';
import router from '@/router';

// Define the Customer interface
interface Customer {
  id: number;
  name: string;
  email: string;
  phNo: string;
  tags: string[];
}

const customers = ref<Customer[]>([]);
const selectedCustomer = ref<Customer | null>(null);
const dialogVisible = ref(false);

const headers = [
  { title: 'Name', value: 'name' },
  { title: 'Email', value: 'email' },
  { title: 'Phone Number', value: 'phNo' },
  { title: 'Tags', value: 'tags' },
  { title: 'Actions', value: 'actions', sortable: false }
];

const fetchCustomers = async () => {
  const response = await customerService.getAllCustomers();
  customers.value = response.data;
};

const openDialog = () => {
  selectedCustomer.value = null;
  dialogVisible.value = true;
};

const editCustomer = (customer: Customer) => {
  selectedCustomer.value = customer;
  dialogVisible.value = true;
};

const deleteCustomer = async (customer: { id: number }) => {
  await customerService.deleteCustomer(customer.id);
  fetchCustomers();
  console.log('Customer deleted');
};

const viewCustomer = (id: number) => {
  router.push(`/customers/${id}`);
};

onMounted(fetchCustomers);
</script>
