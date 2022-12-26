package com.works.services;

import com.works.entities.Customer;
import com.works.entities.Role;
import com.works.repositories.CustomerRepository;
import com.works.utils.REnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerDetailService implements UserDetailsService {

    final CustomerRepository customerRepository;
    final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> optionalCustomer = customerRepository.findByUsernameEqualsIgnoreCase(username);
        if ( optionalCustomer.isPresent() ) {
            Customer c = optionalCustomer.get();
            return new User(
                 c.getUsername(), c.getPassword(), c.getEnable(), true, true, true, parseRole(c.getRoles())
            );
        }else {
            throw new UsernameNotFoundException("Username Not Found");
        }
    }

    private Collection<? extends GrantedAuthority> parseRole(Set<Role> roles) {
        List<GrantedAuthority> ls = new ArrayList<>();
        for( Role role : roles ) {
            ls.add( new SimpleGrantedAuthority(role.getName()));
        }
        return ls;
    }


    public ResponseEntity register(Customer customer) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);
        hm.put(REnum.status, true);
        hm.put(REnum.result, customer);
        return new ResponseEntity(hm, HttpStatus.OK);
    }


}
