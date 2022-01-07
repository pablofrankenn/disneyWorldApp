package com.challange.disneyWorldApp.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.challange.disneyWorldApp.dto.JwtDto;
import com.challange.disneyWorldApp.dto.LoginUsuarioDto;
import com.challange.disneyWorldApp.dto.NuevoUsuarioDto;
import com.challange.disneyWorldApp.entities.Usuario;
import com.challange.disneyWorldApp.security.JwtProvider;
import com.challange.disneyWorldApp.services.EmailService;
import com.challange.disneyWorldApp.services.RolService;
import com.challange.disneyWorldApp.services.UsuarioService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	RolService rolService;

	@Autowired
	JwtProvider jwtProvider;
        
        @Autowired
        private EmailService emailService;

	
	@PostMapping("/register")
	public ResponseEntity<?> nuevoUsuario( @RequestBody NuevoUsuarioDto nuevoUsuario,
			BindingResult bindingResult) throws Exception {
                

		if (usuarioService.existsByUsuario(nuevoUsuario.getNombreUsuario())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ese nombre ya existe");
		}
		if (usuarioService.existsByEmail(nuevoUsuario.getEmail())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ese email ya existe");
		}

		Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(),
				nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));



		usuarioService.save(usuario);
                //Servicio comentado ya que no funciona sin ApiKey
                //emailService.SendMail(usuario.getEmail());
		return new ResponseEntity<>("Usuario creado", HttpStatus.CREATED);

	}

	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuarioDto loginUsuario, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Campos mal");
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity<>(jwtDto, HttpStatus.OK);
	}
}