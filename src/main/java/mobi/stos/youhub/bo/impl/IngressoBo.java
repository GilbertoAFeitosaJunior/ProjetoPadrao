package mobi.stos.youhub.bo.impl;

import java.util.List;
import mobi.stos.youhub.bean.Ingresso;
import mobi.stos.youhub.bo.IIngressoBo;
import mobi.stos.youhub.common.AbstractService;
import mobi.stos.youhub.common.IOperations;
import mobi.stos.youhub.dao.IIngressoDao;
import mobi.stos.youhub.enumm.SituacaoConvidadoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngressoBo extends AbstractService<Ingresso> implements IIngressoBo {

    @Autowired
    private IIngressoDao dao;

    @Override
    protected IOperations<Ingresso> getDao() {
        return dao;
    }

    @Override
    public List<Ingresso> listConvidados(Long idEvento, SituacaoConvidadoEnum situacao) {
        return dao.listConvidados(idEvento, situacao);
    }

    @Override
    public List<Ingresso> listFalta(Long idEvento) {
        return dao.listFalta(idEvento);
    }

    @Override
    public List<Ingresso> listPresente(Long idEvento) {
        return dao.listPresente(idEvento);
    }

}