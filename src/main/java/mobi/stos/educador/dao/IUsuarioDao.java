package mobi.stos.educador.dao;

import mobi.stos.educador.bean.Usuario;
import mobi.stos.educador.common.IOperations;

public interface IUsuarioDao extends IOperations<Usuario> {

    Usuario byEmail(String email);

    Usuario byHash(String hash);


}