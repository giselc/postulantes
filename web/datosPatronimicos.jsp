<form method="post"  id="formulario1" <% if (request.getParameter("ci")==null){ out.print("action='alerta(Debe crear un postulante previamente)'");} else{out.print("action='modificarDatosPat'");} %> enctype="multipart/form-data">
    <table>
        <tr>
            <td>
                <h3>Datos Patron&iacute;micos:</h3>
            </td>
        </tr>
        <tr>
            <td>
                <h4>Padre:</h4>
            </td>
        </tr>
        <tr>
            <td>Nombre Completo: </td>
            <td><input type=text name="PNombreComp" size="50" value="<%if( p!=null){out.print(p.getpNombreComp());} %>" /></td>
        </tr>
        <tr>
            <td>Fecha de Nacimiento: </td>
            <td><input type=date name="PFechaNac" size="8" value="<%if( p!=null){out.print(p.getpFechaNac());} %>"/></td>
        </tr>
        <tr>
            <td>Departamento de Nacimiento: </td>
            <td>
                <select name="PDepartamentoNac" form="formulario1">
                    <%
                    ad1 = mc.getDepartamentos();
                    for(Departamento dep1: ad1 ){
                        String s="";
                        if(p!=null && p.getpDepartamentoNac()!=null){
                            if(p.getpDepartamentoNac().getCodigo()==dep1.getCodigo()){
                                s="selected";
                            }
                        }
                        out.print("<option " + s +" value='"+String.valueOf(dep1.getCodigo()) +"'>"+ dep1.getDescripcion() +"</option>");
                    }
                    %>
                 </select>
            </td>
        </tr>
        <tr>
            <td>Localidad de Nacimiento: </td>
            <td><input type=text name="PLocalidadNac" size="50" value="<%if( p!=null){out.print(p.getpLocalidadNac());} %>"/></td>
        </tr>
        <tr>
            <td>Estado Civil: </td>
            <td>
                <select name="PEstadoCivil" form="formulario1">
                    <%
                    ae = mc.getEstadosCiviles();
                    for(EstadoCivil ec: ae ){
                        String s="";
                        if(p!=null && p.getpEstadoCivil()!=null){
                            if(p.getpEstadoCivil().getCodigo()==ec.getCodigo()){
                                s="selected";
                            }
                        }
                        out.print("<option " + s +" value='"+String.valueOf(ec.getCodigo()) +"'>"+ ec.getDescripcion() +"</option>");
                    }
                    %>
                 </select>
            </td>
        </tr>
        <tr>
            <td>Domicilio: </td>
            <td><input type=text name="PDomicilio" size="50" value="<%if( p!=null){out.print(p.getpDomicilio());} %>"/></td>
        </tr>
        <tr>
            <td>Departamento: </td>
            <td>
                <select name="PDepartamento" form="formulario1">
                    <%
                    ad1 = mc.getDepartamentos();
                    for(Departamento dep1: ad1 ){
                        String s="";
                        if(p!=null && p.getpDepartamento()!=null){
                            if( p.getpDepartamento().getCodigo()==dep1.getCodigo()){
                                s="selected";
                            }
                        }
                        out.print("<option " + s +" value='"+String.valueOf(dep1.getCodigo()) +"'>"+ dep1.getDescripcion() +"</option>");
                    }
                    %>
                 </select>
            </td>
        </tr>
        
        <tr>
            <td>Localidad: </td>
            <td><input type=text name="PLocalidad" size="50" value="<%if( p!=null){out.print(p.getpLocalidad());} %>"/></td>
        </tr>
        <tr>
            <td>Tel&eacute;fono: </td>
            <td><input type=text name="PTelefono" value="<%if( p!=null){out.print(p.getPtelefono());} %>"/></td>
        </tr>
        <tr>
            <td>Profesi&oacute;n: </td>
            <td><input type=text name="PProfesion" size="50" value="<%if( p!=null){out.print(p.getpProfesion());} %>"/></td>
        </tr>
        <tr>
            <td>Lugar de trabajo: </td>
            <td><input type=text name="PLugarTrabajo" size="50" value="<%if( p!=null){out.print(p.getpLugarTrabajo());} %>"/></td>
        </tr>
        <tr>
            <td>
                <h4>Madre:</h4>
            </td>
        </tr>
        <tr>
            <td>Nombre Completo: </td>
            <td><input type=text name="MNombreComp" size="50" value="<%if( p!=null){out.print(p.getmNombreComp());} %>"/></td>
        </tr>
        <tr>
            <td>Fecha de Nacimiento: </td>
            <td><input type=date name="MFechaNac" size="8" value="<%if( p!=null){out.print(p.getmFechaNac());} %>"/></td>
        </tr>
        <tr>
            <td>Departamento de Nacimiento: </td>
            <td>
                <select name="MDepartamentoNac" form="formulario1">
                    <%
                    ad1 = mc.getDepartamentos();
                    for(Departamento dep1: ad1 ){
                        String s="";
                        if(p!=null && p.getmDepartamentoNac()!=null){
                            if(p.getmDepartamentoNac().getCodigo()==dep1.getCodigo()){
                                s="selected";
                            }
                        }
                        out.print("<option " + s +" value='"+String.valueOf(dep1.getCodigo()) +"'>"+ dep1.getDescripcion() +"</option>");
                    }
                    %>
                 </select>
            </td>
        </tr>
        <tr>
            <td>Localidad de Nacimiento: </td>
            <td><input type=text name="MLocalidadNac" size="50" value="<%if( p!=null){out.print(p.getmLocalidadNac());} %>"/></td>
        </tr>
        <tr>
            <td>Estado Civil: </td>
            <td>
                <select name="MEstadoCivil" form="formulario1">
                    <%
                    ae = mc.getEstadosCiviles();
                    for(EstadoCivil ec: ae ){
                        String s="";
                        if(p!=null && p.getmEstadoCivil()!=null){
                            if(p.getmEstadoCivil().getCodigo()==ec.getCodigo()){
                                s="selected";
                            }
                        }
                        out.print("<option " + s +" value='"+String.valueOf(ec.getCodigo()) +"'>"+ ec.getDescripcion() +"</option>");
                    }
                    %>
                 </select>
            </td>
        </tr>
        <tr>
            <td>Domicilio: </td>
            <td><input type=text name="MDomicilio" size="50" value="<%if( p!=null){out.print(p.getmDomicilio());} %>"></td>
        </tr>
        <tr>
            <td>Departamento: </td>
            <td>
                <select name="MDepartamento" form="formulario1">
                    <%
                    ad1 = mc.getDepartamentos();
                    for(Departamento dep1: ad1 ){
                        String s="";
                        if(p!=null && p.getmDepartamento()!=null){
                            if(p.getmDepartamento().getCodigo()==dep1.getCodigo()){
                                s="selected";
                            }
                        }
                        out.print("<option " + s +" value='"+String.valueOf(dep1.getCodigo()) +"'>"+ dep1.getDescripcion() +"</option>");
                    }
                    %>
                 </select>
            </td>
        </tr>
        <tr>
            <td>Localidad: </td>
            <td><input type=text name="MLocalidad" size="50" value="<%if( p!=null){out.print(p.getmLocalidad());} %>"/></td>
        </tr>
        <tr>
            <td>Tel&eacute;fono: </td>
            <td><input type=text name="MTelefono" value="<%if( p!=null){out.print(p.getMtelefono());} %>" /></td>
        </tr>
        <tr>
            <td>Profesi&oacute;n: </td>
            <td><input type=text name="MProfesion" size="50" value="<%if( p!=null){out.print(p.getmProfesion());} %>"/></td>
        </tr>
        <tr>
            <td>Lugar de trabajo: </td>
            <td><input type=text name="MLugarTrabajo" size="50" value="<%if( p!=null){out.print(p.getmLugarTrabajo());} %>"/></td>
        </tr>
        <tr>
            <td><input name= "ci" hidden="hidden" type="text" value="<%= request.getParameter("ci") %>"/></td>
        </tr>
    </table>
        <p align="right"> <input style="font-size: 18px" type="submit" value='Modificar' > 
</form>