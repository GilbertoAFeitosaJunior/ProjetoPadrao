<%@taglib prefix="s" uri="/struts-tags"%>
<ul class="sidebar-menu" id="nav-accordion">

    <li>
        <a href="#"  class="active">
            <i class="fa fa-dashboard"></i>
            <span>Dashboard</span>
        </a>
    </li>

    <li>
        <a href="listUsuario" <s:if test='menu == "Usuario"'> class="active"</s:if>>
            <i class="fa fa-user"></i>
            <span>Usu�rios</span>
        </a>
    </li>

    <li>
        <a href="logout">
            <i class="fa fa-remove"></i>
            <span>Sair</span>
        </a>
    </li>
</ul>


