package com.java.springboot_with_vuejs.service;

import com.java.springboot_with_vuejs.dto.UserDTO;
import com.java.springboot_with_vuejs.entity.Tag;
import com.java.springboot_with_vuejs.entity.User;
import com.java.springboot_with_vuejs.entity.UserTag;
import com.java.springboot_with_vuejs.repository.TagRepository;
import com.java.springboot_with_vuejs.repository.UserRepository;
import com.java.springboot_with_vuejs.repository.UserTagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;
    
    @Autowired
    private UserTagRepository userTagRepository;

    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        User user = mapDTOToUser(userDTO);
        user = userRepository.save(user);
        addTagsToUser(user, userDTO.getTags());
        return mapUserToDTO(user);
    }
    
    @Transactional
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update user information
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhNo(userDTO.getPhNo());
        user.setCreatedAt(userDTO.getCreatedAt());
        user.setUpdatedAt(LocalDateTime.now());
        user.setDeleted(userDTO.isDeleted());

        // Update tags
        Set<String> newTags = userDTO.getTags();
        Set<UserTag> existingUserTags = user.getUserTags();
        Set<String> existingTagNames = new HashSet<>();

        // Collect existing tag names
        for (UserTag userTag : existingUserTags) {
            existingTagNames.add(userTag.getTag().getName());
        }

        // Add new tags and remove those not in the new set
        for (String tagName : newTags) {
            if (!existingTagNames.contains(tagName)) {
                Tag tag = tagRepository.findByName(tagName).orElseGet(() -> {
                    Tag newTag = new Tag(tagName, LocalDateTime.now(), LocalDateTime.now(), false);
                    return tagRepository.save(newTag);
                });
                UserTag newUserTag = new UserTag(user, tag);
                existingUserTags.add(newUserTag);
                userTagRepository.save(newUserTag);
            }
        }

        // Remove tags that are not in the new set
        existingUserTags.removeIf(userTag -> !newTags.contains(userTag.getTag().getName()));

        user = userRepository.save(user);
        return mapUserToDTO(user);
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setDeleted(true);
        userRepository.save(user);
    }

    public Set<UserDTO> getAllUsers() {
        List<User> users = userRepository.findByDeletedFalse();
        Set<UserDTO> userDTOs = new HashSet<>();
        for (User user : users) {
            userDTOs.add(mapUserToDTO(user));
        }
        return userDTOs;
    }

    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapUserToDTO(user);
    }

    @Transactional
    private void addTagsToUser(User user, Set<String> tagNames) {
        for (String tagName : tagNames) {
            Tag tag = tagRepository.findByName(tagName)
                    .orElseGet(() -> {
                        Tag newTag = new Tag(tagName, LocalDateTime.now(), LocalDateTime.now(), false);
                        return tagRepository.save(newTag);
                    });
            UserTag userTag = new UserTag(user, tag);
            user.getUserTags().add(userTag);
        }
    }

    private User mapDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhNo(userDTO.getPhNo());
        user.setCreatedAt(userDTO.getCreatedAt());
        user.setUpdatedAt(userDTO.getUpdatedAt());
        user.setDeleted(userDTO.isDeleted());
        return user;
    }

    private UserDTO mapUserToDTO(User user) {
        Set<String> tags = new HashSet<>();
        for (UserTag userTag : user.getUserTags()) {
            tags.add(userTag.getTag().getName());
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhNo(user.getPhNo());
        userDTO.setTags(tags);
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedAt(user.getUpdatedAt());
        userDTO.setDeleted(user.isDeleted());
        return userDTO;
    }
}
