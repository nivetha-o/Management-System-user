import axios from 'axios';

const apiClient = axios.create({
  baseURL: 'http://localhost:8080/api/tags',
  headers: {
    'Content-type': 'application/json',
  },
});

export default {
  getAllTags() {
    return apiClient.get('');
  },
  getTagById(id: number) {
    return apiClient.get(`/${id}`);
  },
  creatTag(data: { name: string }) {
    return apiClient.post('', data);
  },
  updateTag(id: number, data: { name: string}) {
    return apiClient.put(`/${id}`, data);
  },
  deleteTag(id: number) {
    return apiClient.delete(`/${id}`);
  }
};
