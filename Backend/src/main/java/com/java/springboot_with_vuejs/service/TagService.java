package com.java.springboot_with_vuejs.service;

import com.java.springboot_with_vuejs.dto.TagDTO;
import com.java.springboot_with_vuejs.entity.Tag;
import com.java.springboot_with_vuejs.repository.TagRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;
    
    public List<TagDTO> getAllTags() {
        List<Tag> tags = tagRepository.findByDeletedFalse();
        return tags.stream()
                   .map(this::convertToDTO)
                   .collect(Collectors.toList());
    }

    public TagDTO getTagById(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        return convertToDTO(tag);
    }

    public TagDTO createTag(TagDTO tagDTO) {
        Tag tag = convertToEntity(tagDTO);
        tag = tagRepository.save(tag);
        return convertToDTO(tag);
    }

    public TagDTO updateTag(Long id, TagDTO tagDTO) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));

        tag.setName(tagDTO.getName());
        tag.setDeleted(tagDTO.isDeleted());

        tag = tagRepository.save(tag);
        return convertToDTO(tag);
    }

    public void deleteTag(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        tag.setDeleted(true);
        tagRepository.save(tag);
    }

    private TagDTO convertToDTO(Tag tag) {
        TagDTO tagDTO = new TagDTO();
        BeanUtils.copyProperties(tag, tagDTO);
        return tagDTO;
    }

    private Tag convertToEntity(TagDTO tagDTO) {
        Tag tag = new Tag();
        BeanUtils.copyProperties(tagDTO, tag);
        return tag;
    }
}
