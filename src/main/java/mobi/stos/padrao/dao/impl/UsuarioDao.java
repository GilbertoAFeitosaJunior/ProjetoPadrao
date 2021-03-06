package mobi.stos.padrao.dao.impl;

import java.util.List;
import mobi.stos.padrao.bean.Usuario;
import mobi.stos.padrao.common.AbstractHibernateDao;
import mobi.stos.padrao.dao.IUsuarioDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDao extends AbstractHibernateDao<Usuario> implements IUsuarioDao {

    public UsuarioDao() {
        super(Usuario.class);
    }

    @Override
    public Usuario byEmail(String email) {
        Criteria criteria = getCurrentSession().createCriteria(Usuario.class);
        criteria.add(Restrictions.eq("email", email).ignoreCase());
        criteria.setMaxResults(1);
        return (Usuario) criteria.uniqueResult();
    }

    @Override
    public Usuario byHash(String hash) {
        Criteria criteria = getCurrentSession().createCriteria(Usuario.class);
        criteria.add(Restrictions.eq("hash", hash));
        criteria.setMaxResults(1);
        return (Usuario) criteria.uniqueResult();
    }

}
