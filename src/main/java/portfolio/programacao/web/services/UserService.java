package portfolio.programacao.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import portfolio.programacao.web.entities.User;
import portfolio.programacao.web.repositories.UserRepository;
import portfolio.programacao.web.services.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        try {
            return userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
    }

    public User insert(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
    }

    public User update(Long id, User user) {
        try {
            User existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
            existingUser.setNome(user.getNome());
            existingUser.setEmail(user.getEmail());
            existingUser.setTelefone(user.getTelefone());
            existingUser.setPassword(user.getPassword());
            return userRepository.save(existingUser);
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
    }

}
