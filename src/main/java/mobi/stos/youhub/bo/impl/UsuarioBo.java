package mobi.stos.youhub.bo.impl;

import java.util.Date;
import java.util.List;
import mobi.stos.youhub.bean.Usuario;
import mobi.stos.youhub.bo.IUsuarioBo;
import mobi.stos.youhub.common.AbstractService;
import mobi.stos.youhub.common.IOperations;
import mobi.stos.youhub.dao.IUsuarioDao;
import mobi.stos.youhub.exception.AvoidDuplicationEmailException;
import mobi.stos.youhub.exception.LoginException;
import mobi.stos.youhub.exception.SenhaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioBo extends AbstractService<Usuario> implements IUsuarioBo {

    @Autowired
    private IUsuarioDao dao;

    @Override
    protected IOperations<Usuario> getDao() {
        return dao;
    }

    @Override
    public Usuario login(String login, String senha) throws LoginException, SenhaException {
        Usuario usuario = dao.byEmail(login);
        if (usuario == null) {
            return null;
        }
        if (!usuario.getSenha().equals(senha)) {
            return null;
        }
        usuario.setUltimoAcesso(new Date());
        this.persist(usuario);
        return usuario;
    }

    @Override
    public Usuario byHash(String hash) {
        return dao.byHash(hash);
    }

    @Override
    public Usuario cadastrar(Usuario usuario) throws AvoidDuplicationEmailException {
        System.out.println("chegou aqui...");
        if (dao.byEmail(usuario.getEmail()) != null) {
            throw new AvoidDuplicationEmailException();
        }
        return dao.persist(usuario);
    }

    @Override
    public Usuario byEmail(String email) {
        return dao.byEmail(email);
    }

    @Override
    public List<Usuario> listConsultorByManager(Long idMananger) {
        return dao.listConsultorByManager(idMananger);
    }

    @Override
    public Usuario loadByConsultor(Long idConsultor) {
       return dao.loadByConsultor(idConsultor);
    }

}
