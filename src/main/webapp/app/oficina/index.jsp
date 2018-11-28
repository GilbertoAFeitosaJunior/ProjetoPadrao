<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<compress:html compressCss="true">

    <%@include file="../fragment/head.jsp" %>

    <section class="panel">
        <header class="panel-heading">
            Pesquisar Oficina
            <span class="tools pull-right">
                <a href="javascript:;" class="fa fa-chevron-down"></a>
            </span>
        </header>

        <div class="panel-body">
            <s:form acceptcharset="UTF-8" method="post" action="listOficina" theme="simple">
                <div class="row">

                    <div class="col-lg-3">
                        <div class="form-group">
                            <label>Pesquisar por</label>
                            <div class="">
                                <s:select id="consulta.campo"
                                          name="consulta.campo"
                                          list="camposConsultaEnum"
                                          listKey="key"
                                          listValue="value"
                                          cssClass="form-control m-bot15"
                                          />
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="form-group">
                            <label>Exibir</label>
                            <div class="">
                                <s:select id="consulta.limiteResultados"
                                          name="consulta.limiteResultados"
                                          list="limiteResultadoEnum"
                                          listKey="quantidade"
                                          listValue="descricao"
                                          cssClass="form-control m-bot15"
                                          />
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label>&nbsp;</label>
                            <div class="input-group m-bot15 right">
                                <s:textfield name="consulta.valor" id="consulta.valor" placeholder="Digite o que você quer encontrar" cssClass="form-control" />
                                <span class="input-group-btn">
                                    <button type="submit" class="btn btn-white"><i class="fa fa-search"></i> Buscar...</button>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </s:form>

        </div>
    </section>

    <section class="panel">
        <header class="panel-heading">Registros</header>
            <!--s:if test="usuario.educador != null"-->
            <div class="panel-body pull-right">
                <button class="btn btn-primary" onclick="list.add();">
                    <i class="fa fa-plus"></i> 
                    Adicionar Novo
                </button>
            </div>
        <!--/s:if-->

        <div class="panel-body">
            <table class="table table-striped table-advance table-hover table-bordered ">
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Escola</th>
                        <th>Data de planejamento</th>
                        <th>Turno</th>
                        <th>Situação</th>
                        <th class="col-lg-1 col-sm-1 col-xs-1 text-right">A&ccedil;&otilde;es</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="oficinas">
                        <tr>
                            <td><s:property value="nome"/></td>
                            <td><s:property value="escola.nome"/></td>
                            <td ><s:property value="dataPlanejada"/></td>
                            <td ><s:property value="turnoEnum.name"/></td>
                            <td ><s:property value="situacaoEnum.name"/></td>
                            <td class="text-right">
                                    <!-- s:if test="usuario.educador != null" --> 
                                    <div class="btn-group btn-group-justified">
                                        <a class="btn btn-primary btn-xs" onclick="list.edit(<s:property value="id" />);">
                                            <i class="fa fa-edit"></i>
                                        </a>
                                        <a class="btn btn-danger btn-xs" data-toggle="modal" href="#deleteModal" onclick="list.remove(<s:property value="id" />);">
                                            <i class="fa fa-trash-o "></i>
                                        </a>
                                    <!-- /s:if--><!--s:else -->
                                        <!--button class="btn btn-danger btn-xs btn-block" onclick="list.view('<!s:property value="id" />');">
                                            <i class="fa fa-eye"></i> Visualizar 
                                        </button>
                                    <!--/s:else-->
                                </div>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </div>

        <div class="panel-body">
            <div>
                <ul class="pagination pagination-sm pull-right" id="pagination"></ul>
            </div>
        </div>
    </section>

    <!-- delete modal -->
    <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Excluir?</h4>
                </div>
                <div class="modal-body">
                    Deseja excluir esse registro?<br/>
                    Atenção! 
                    Todos os arquivos anexados também serão excluídos!
                </div>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn-default" type="button">Não</button>
                    <button class="btn btn-warning" type="button" onclick="list.confirmDelete()">Sim</button>
                </div>
            </div>
        </div>
    </div>
    <!-- delete modal -->

    <%@include file="../fragment/endpage.jsp" %>
    <script type="text/javascript" src="../js/pagination.js"></script>
    <script type="text/javascript">
                        _helperID = null;
                        var list = {
                            view: function (id){
                                window.location = "prepareVisualizarOficina?oficina.id=" + id;  
                            },
                            add: function () {
                                window.location = "prepareOficina";
                            },
                            edit: function (id) {
                                window.location = "prepareOficina?oficina.id=" + id;
                            },
                            remove: function (id) {
                                _helperID = id;
                            },
                            confirmDelete: function () {
                                window.location = "deleteOficina?oficina.id=" + _helperID;
                            }
                        };

                        $(function () {
                            paginate.create({
                                "limite": "${consulta.limiteResultados}",
                                "atual": "${consulta.paginaAtual}",
                                "paginas": "${consulta.paginas}",
                                "operador": "${consulta.operador}",
                                "campo": "${consulta.campo}",
                                "valor": "${consulta.valor}",
                                "url": "listOficina"
                            });
                        });
    </script>
    <%@include file="../fragment/truend.jsp" %>

</compress:html>

