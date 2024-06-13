import axios from 'axios';

const apiClient = axios.create({
  baseURL: 'http://localhost:8080/api/users',
  headers: {
    'Content-type': 'application/json',
  },
});

export default {
  getAllCustomers() {
    return apiClient.get('');
  },
  getCustomerById(id: number) {
    return apiClient.get(`/${id}`);
  },
  createCustomer(data: { name: string, email: string, phNo:string, tags: string[] }) {
    return apiClient.post('', data);
  },
  updateCustomer(id: number, data: { name: string, email: string, phNo:string, tags: string[] }) {
    return apiClient.put(`/${id}`, data);
  },
  deleteCustomer(id: number) {
    return apiClient.delete(`/${id}`);
  }
};
