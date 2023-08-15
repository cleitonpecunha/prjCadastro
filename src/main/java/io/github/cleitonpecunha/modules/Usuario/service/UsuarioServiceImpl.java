package io.github.cleitonpecunha.modules.Usuario.service;

import io.github.cleitonpecunha.database.entitys.Usuario;
import io.github.cleitonpecunha.database.repositories.Usuarios;
import io.github.cleitonpecunha.config.exception.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Usuarios usuariosRepository;

    @Transactional
    public Usuario salvarUsuario(Usuario usuario ) {

        if( usuariosRepository.existsByLogin(usuario.getLogin())) {
            throw new RegraNegocioException("O login informado já se encontra cadastrado");
        }
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(usuario.getNome());
        novoUsuario.setLogin(usuario.getLogin());
        novoUsuario.setSenha(senhaCriptografada);
        usuariosRepository.save(novoUsuario);
        return novoUsuario;
    }

    public UserDetails autenticar( Usuario usuario ) {

        UserDetails user = loadUserByUsername(usuario.getLogin());
        boolean senhasBatem = passwordEncoder.matches(usuario.getSenha(), user.getPassword());

        //System.out.println(user.getAuthorities());

        if (senhasBatem) {
            return user;
        }
        throw new RegraNegocioException("Senha do usuário informada é inválida");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuariosRepository
                .findByLogin(username)
                .orElseThrow( () -> new UsernameNotFoundException("Usuário não encontrado na base."));

        //System.out.println("admin?:"+usuario.isAdmin());

        String[] roles = usuario.isAdmin()
                ? new String[]{"ADMIN","USER"}
                : new String[]{"USER"};

        //System.out.println("roles:"+roles);

        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }

}
