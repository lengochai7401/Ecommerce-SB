package dev.khomuc.library.service;

import dev.khomuc.library.dto.AdminDto;
import dev.khomuc.library.model.Admin;
import dev.khomuc.library.repository.AdminRepository;

public interface AdminService {
    Admin findByUsername(String username);
    Admin save(AdminDto adminDto);
}
