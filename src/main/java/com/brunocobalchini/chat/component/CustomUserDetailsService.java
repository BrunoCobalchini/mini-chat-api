package com.brunocobalchini.chat.component;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.brunocobalchini.chat.model.User;
import com.brunocobalchini.chat.repository.UserRepository;

import reactor.core.publisher.Mono;

@Service
public class CustomUserDetailsService implements ReactiveUserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Mono<UserDetails> findByUsername(final String username) {
		Optional<User> usu = userRepository.findByEmail(username);
		if (usu.isPresent()) {
			return Mono.just(usu.get()).map(UserAccount::new);
		} else {
			return Mono.empty();
		}
	}

	public class UserAccount implements UserDetails {

		private static final long serialVersionUID = 1L;

		private transient User user;

		public UserAccount(User user) {
			this.user = user;
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return Collections.singletonList(new SimpleGrantedAuthority("USER"));
		}

		@Override
		public String getPassword() {
			return user.getPassword();
		}

		@Override
		public String getUsername() {
			return user.getEmail();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}
	}

}