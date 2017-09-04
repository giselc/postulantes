<script>
    function verificarEdad(input){
        var fecha= input.value;
        var values=fecha.split("-");
        var dia = values[2];
        var mes = values[1];
        var ano = values[0];
 
        // cogemos los valores actuales
        var fecha_hoy = new Date();
        var ahora_ano = fecha_hoy.getYear();
        var ahora_mes = fecha_hoy.getMonth()+1;
        var ahora_dia = fecha_hoy.getDate();
 
        // realizamos el calculo
        var edad = (ahora_ano + 1900) - ano;
        if ( ahora_mes < mes )
        {
            edad--;
        }
        if ((mes == ahora_mes) && (ahora_dia < dia))
        {
            edad--;
        }
        if (edad > 1900)
        {
            edad -= 1900;
        }
 
        // calculamos los meses
        var meses=0;
        if(ahora_mes>mes)
            meses=ahora_mes-mes;
        if(ahora_mes<mes)
            meses=12-(mes-ahora_mes);
        if(ahora_mes==mes && dia>ahora_dia)
            meses=11;
 
        // calculamos los dias
        var dias=0;
        if(ahora_dia>dia)
            dias=ahora_dia-dia;
        if(ahora_dia<dia)
        {
            ultimoDiaMes=new Date(ahora_ano, ahora_mes, 0);
            dias=ultimoDiaMes.getDate()-(dia-ahora_dia);
        }
        if(edad<18){
            alert("El postulante es menor de edad: "+edad+" años\nRECUERDE COMPLETAR FORMULARIO DE DATOS PATRONÍMICOS\nUNA VEZ QUE FINALICE EL DE DATOS PERSONALES");
        }
        
    }
</script>
<form method="post" target="_blank" name="formComando" action='Listar'>
    <table style="float: right">
        <tr>
            <td><input type=number name='List[]' size="8" hidden="hidden" value=<%if( p!=null){ out.print(Integer.valueOf(p.getCi()));} %> /></td>
        </tr>
        <tr>
            <td><input type="image" width="30%" <%if( p==null){out.print("hidden='hidden'");}%>title="Imprimir ficha"src="images/imprimir.png" alt="Submit Form" /></td>
        </tr>
    </table>
</form>
<div id="loader" style="position: fixed; top:0; left:0; width:100%; height: 100%;background: url('images/loading-verde.gif') center center no-repeat; background-size: 10%"></div>
<form method="post"  name="formulario" id="formulario" onsubmit="<% if (request.getParameter("ci")==null){out.print("alert('Se habilitó la pestaña de Datos Patronímicos');");} %>return agregarimagen(this);" <% if (request.getParameter("ci")==null){ out.print("action='guardarDatosBasicos'");} else{out.print("action='modificarDatosBasicos'");} %> enctype="multipart/form-data">
    <table border="0">
        <tr>
            <p id="mensaje" style="color: #990000"><% if(session.getAttribute("Mensaje")!=null){out.print("<img src='images/icono-informacion.png' width='3%' /> &nbsp;&nbsp;"+session.getAttribute("Mensaje"));}%></p>
            <%
                session.setAttribute("Mensaje",null);
            %>
        </tr>
        <tr>
            <h3>Datos Personales:</h3>
        </tr>
        <tr>
            <td>Foto tipo pasaporte: </td>
            <td><input type="file" name="foto" id="uploadImage" onchange="PreviewImage(); canvasimage('uploadImage','canvas6');" accept="image/*"/></td>
            <td><canvas id="canvas6" hidden="hidden" > </canvas></td>
        </tr>
        <tr  <% if(p==null){out.println("hidden='hidden'");} %>>
            <td>ID: </td>
            <td><input type="text" name="id" value="<%if(p!=null){out.print(p.getId());} %>" size="3" style="text-align: center" readonly/>
                <input type="text" name="carreraString" value="<%if(p!=null){out.print(p.getCarrera().getDescripcion());} %>" size="30" style="text-align: center" readonly/>
            </td>
            
        </tr>
        <tr  <% if(p==null){out.println("hidden='hidden'");} %>>
            <td>Unidad Inscriptora: </td>
            <td><input type=text size= 35 value="<%if( p!=null){ out.print(p.getUnidadInsc().getNombreMostrar());} %>" readonly style="text-align: center"/></td>
            <td><input type=text name="unidadInsc" size= 35 value="<%if( p!=null){ out.print(p.getUnidadInsc().getId());} %>"style="text-align: center" hidden="hidden"/></td>
        </tr >
        <tr>
            <td>C.I.: </td>
            <td><input type=number name="ci" size="8" maxlength="8" value=<%if( p!=null){ out.print(Integer.valueOf(p.getCi()));} %> required="required"/></td>
        </tr>
        <tr>
            <td>Primer nombre: </td>
            <td><input type="text" name="primerNombre" value="<%if( p!=null){out.print(p.getPrimerNombre());} %>" size="50" required="required"/></td>
        </tr>
        <tr>
            <td>Segundo nombre: </td>
            <td><input type="text" name="segundoNombre" value="<%if( p!=null){out.print(p.getSegundoNombre());} %>" size="50"/></td>
        </tr>
        <tr>
            <td>Primer apellido: </td>
            <td><input type="text" name="primerApellido" value="<%if( p!=null){out.print(p.getPrimerApellido());} %>" size="50" required="required"/></td>
        </tr>
        <tr>
            <td>Segundo apellido: </td>
            <td><input type="text" name="segundoApellido" value="<%if( p!=null){out.print(p.getSegundoApellido());} %>" size="50" required="required"/></td>
        </tr>
        <tr>
            <td>Sexo: </td>
            <td>M: <input type="radio" name="sexo[]" value="M" <% if ((p!=null && p.getSexo().equals("M"))||(p==null)){ out.print("checked='checked'");} %>/>
                F: <input type="radio" name="sexo[]" value="F" <% if ((p!=null && p.getSexo().equals("F"))){ out.print("checked='checked'");} %> /></td>
        </tr>
        <tr>
            <td>Fecha de Nacimiento: </td>
            <td><input type=date name="fechaNac" onblur="verificarEdad(this);" size="8" value="<%if( p!=null){out.print(p.getFechaNac());} %>"required="required"/></td>
        </tr>
        <tr>
            <td>Departamento de Nacimiento: </td>
            <td>
                <select name="departamentoNac" form="formulario" required="required">
                    <%
                    ArrayList<Departamento> ad = mc.getDepartamentos();
                    for(Departamento dep: ad ){
                        String s="";
                        if(p!=null && p.getDepartamentoNac().getCodigo()==dep.getCodigo()){
                            s="selected";
                        }
                        out.print("<option " + s +" value='"+String.valueOf(dep.getCodigo()) +"'>"+ dep.getDescripcion() +"</option>");
                    }
                    %>
                 </select>
            </td>
        </tr>
        <tr>
            <td>Localidad de Nacimiento: </td>
            <td><input type=text name="localidadNac" value="<%if( p!=null){out.print(p.getLocalidadNac());} %>" size="50" required="required"/></td>
        </tr>
        
        <tr>
            <td>Credencial: </td>
            <td>Serie: <input type=text name="cc" size="8" value="<%if( p!=null){out.print(p.getCc());} %>"/>
                N&deg;: <input type=number name="CCNro" size="8" value="<%if( p!=null){out.print(p.getCcNro());} %>"/></td>
        </tr>
        <tr>
            <td>Estado Civil: </td>
            <td>
                <select name="estadoCivil" form="formulario" required="required">
                    <%
                    ArrayList<EstadoCivil> ae = mc.getEstadosCiviles();
                    for(EstadoCivil ec: ae ){
                        String s="";
                        if(p!=null && p.getEstadoCivil().getCodigo()==ec.getCodigo()){
                            s="selected";
                        }
                        out.print("<option " + s +" value='"+String.valueOf(ec.getCodigo()) +"'>"+ ec.getDescripcion() +"</option>");
                    }
                    %>
                 </select>
            </td>
        </tr>
        <tr>
            <td>Domicilio: </td>
            <td><input type=text name="domicilio" value="<%if( p!=null){out.print(p.getDomicilio());} %>" size="50"/></td>
        </tr>
        <tr>
            <td>Departamento: </td>
            <td>
                <select name="departamento" form="formulario">
                    <%
                    ArrayList<Departamento> ad1 = mc.getDepartamentos();
                    for(Departamento dep1: ad1 ){
                        String s="";
                        if(p!=null && p.getDepartamento().getCodigo()==dep1.getCodigo()){
                            s="selected";
                        }
                        out.print("<option " + s +" value='"+String.valueOf(dep1.getCodigo()) +"'>"+ dep1.getDescripcion() +"</option>");
                    }
                    %>
                 </select>
            </td>
        </tr>
        <tr>
            <td>Localidad: </td>
            <td><input type=text name="localidad" value="<% if( p!=null){out.print(p.getLocalidad());} %>" size="50"/></td>
        </tr>
        <tr>
            <td>Tel&eacute;fono: </td>
            <td><input type=text name="telefono" size="8" value="<% if(p!=null){out.print(p.getTelefono());} %>" required="required"/></td>
        </tr>
        <tr>
            <td>Correo Electr&oacute;nico: </td>
            <td><input type=text name="email" size="50" value="<%if( p!=null){out.print(p.getEmail());} %>" required="required"/></td>
        </tr>
        <tr>
            <td>Re-ingreso: </td>
            <td><input  type=checkbox name="reingreso" <% if(p!=null && p.isReingreso()){out.print("checked='checked'");} %>/></td>
        </tr>
        <tr>
            <td>A cursar en el instituto: </td>
            <td>
                Secundaria:
                <input type=radio name="aprobado[]" value="5" onclick="showOrientacion(true)" size="8" <% if(p!=null && p.isQuinto()){out.print("checked='checked'");} %>/>
                6to aprobado:
                <input type=radio name="aprobado[]" value="6" onclick="showOrientacion(false)"size="8"  <% if((p!=null && !p.isQuinto())||(p==null)){ out.print("checked='checked'");} %>/>
            </td>

        </tr>
        <tr id="orien" <% if((p!=null && !p.isQuinto())||(p==null)){out.print("style='display: none'");} %> >
            <td>Orientaci&oacute;n: </td>
            <td>
                <select name="orientacion" form="formulario">
                    <%
                    ArrayList<Orientacion> ao = mc.getOrientaciones();
                    for(Orientacion o: ao ){
                        String s="";
                        if(p!=null && p.getOrientacion().getCodigo()==o.getCodigo()){
                            s="selected";
                        }
                        out.print("<option " + s +" value='"+String.valueOf(o.getCodigo()) +"'>"+ o.getDescripcion() +"</option>");
                    }
                    %>
                 </select>
            </td>
        </tr>
        
        <% int i = Integer.parseInt(session.getAttribute("usuarioID").toString());%>
        <tr>
            <td>LMGA: </td>
            <td><input  id="lmga" onchange="showPaseDirecto(<% out.print(session.getAttribute("usuarioID").toString()); %> , <%out.print(u.isAdmin()); %>);" type=checkbox name="lmga" <% if(p!=null && p.isLmga()){out.print("checked='checked'");} %>/></td>
        </tr>
        <tr id="pd" <% if (session.getAttribute("usuarioID").toString() != "13" && ((p!=null && !p.isLmga())||(p==null)) ) {out.print("style='display: none'");} %> >
            <td>Pase directo: </td>
            <td><input id="paseDirecto" onchange="showNotaPaseDirecto(<% out.print(session.getAttribute("usuarioID").toString()); %> , <%out.print(u.isAdmin()); %>);" type=checkbox name="paseDirecto" <% if(p!=null && p.isPaseDirecto()){out.print("checked='checked'");} %>/></td>
        </tr>
        <tr id="notaPaseDirecto" <% if (session.getAttribute("usuarioID").toString() != "13" && ((p!=null && !p.isPaseDirecto())||(p==null)) ) {out.print("style='display: none'");} %> >
            <td>Nota: </td>
            <td><input type=number name="notaPaseDirecto" step="0.001" value="<% if( p!=null){out.print(p.getNotaPaseDirecto());}else{out.print("0");} %>"/></td>
        </tr>
        <tr>
            <td>Materias Pendientes: </td>
            <td>
                <textarea rows="4" cols="50" name="materiasPendientes" form="formulario"><% if(p!=null){out.print(p.getMateriasPendientes());} %></textarea>
            </td>
        </tr>
        <%--<tr>
            <td>Presenta certificado de Buena Conducta: </td>
            <td><input type=checkbox name="buenaConducta" <% if(p!=null && p.isBuenaConducta()){out.print("checked='checked'");} %>/></td>
        </tr>--%>
        <tr>
            <td>Personal Subalterno: </td>
            <td><input id="ps" type=checkbox onchange="showPsEjercito();" name="ps" <% if(p!=null && p.isPs()){out.print("checked='checked'");} %>/></td>
        </tr>
        <tr id="psEjer"  <% if((p!=null && !p.isPs())||(p==null)){out.print("style='display: none'");} %> >
            <td>Personal Subalterno del Ej&eacute;rcito: </td>
            <td><input type=checkbox name="psEjercito" <% if(p!=null && p.isPsEjercito()){out.print("checked='checked'");} %>/></td>
        </tr>
        <tr id="hijos" <% if((p!=null && !p.isPs())||(p==null)){out.print("style='display: none'");} %> >
            <td>Cantidad de hijos: </td>
            <td>
                0:
                <input type=radio name="hijos[]" value="0" <% if((p!=null && p.getHijos()==0) || (p==null) ){out.print("checked='checked'");} %>/>
                1:
                <input type=radio name="hijos[]" value="1" <% if(p!=null && p.getHijos()==1){ out.print("checked='checked'");} %>/> 
                2:
                <input type=radio name="hijos[]" value="2" <% if(p!=null && p.getHijos()==2){ out.print("checked='checked'");} %>/> 
                3:
                <input type=radio name="hijos[]" value="3" <% if(p!=null && p.getHijos()==3){ out.print("checked='checked'");} %>/> 
                + de 3:
                <input type=radio name="hijos[]" value="4" <% if(p!=null && p.getHijos()==4){ out.print("checked='checked'");} %>/> 
            
            </td>

        </tr>
        <tr>
            <td>Necesita alojamiento: </td>
            <td><input  type=checkbox name="alojamiento" <% if(p!=null && p.isAlojamiento()){out.print("checked='checked'");} %>/></td>
        </tr>
        <tr>
            <td>Observaciones: </td>
            <td>
                <textarea rows="4" cols="50" name="observaciones" form="formulario"><% if(p!=null){out.print(p.getObservaciones());} %></textarea>
            </td>
        </tr>
        <tr>
            <td>
                <h3>Documentos Adjuntos:</h3>
            </td>
        </tr>
        <tr>
            <td>C.I. Anverso: </td>
            <td><input id= "anverso" type="file" name="fotoCIAnverso" accept="image/*" onchange="canvasimage('anverso','canvasAnverso')"/></td>
            <div id="dialogAnverso" style="display:none" title="Cedula de Identidad">
                <img src="<%= src[1] %>" width="100%"/>
            </div>
            <td><canvas id="canvasAnverso" hidden="hidden"> </canvas></td>
            <td><a <% if (!src[1].equals("")){ out.print("onclick='abrir_dialog(dialogAnverso)' style='color: #000099'");} %>>Ver</a></td>
        </tr>
        <tr>
            <td>C.I. Reverso: </td>
            <td><input id= "reverso" type="file" name="fotoCIReverso" accept="image/*" onchange="canvasimage('reverso','canvasReverso')"/></td>
            <div id="dialogReverso" style="display:none" title="Cedula de Identidad">
                <img src="<%= src[2] %>" width="100%"/>
            </div>
            <td><canvas id="canvasReverso" hidden="hidden"> </canvas></td>
            <td><a <% if (!src[2].equals("")){ out.print("onclick='abrir_dialog(dialogReverso)' style='color: #000099'");} %>>Ver</a></td>
        </tr>
        <tr>
            <td>
                </br>
            </td>
        </tr>
        <tr>
            <td>F&oacute;rmula 69 Hoja 1: </td>
            <td><input id= "hoja1" type="file" name="fotoF69Hoja1" accept="image/*" onchange="canvasimage('hoja1','canvasHoja1')"/></td>
            <div id="dialogHoja1" style="display:none" title="Formula 69">
                <img src="<%= src[3] %>"width="100%"/>
            </div>
            <td><canvas id="canvasHoja1" hidden="hidden"> </canvas></td>
            <td><a <% if (!src[3].equals("")){ out.print("onclick='abrir_dialog(dialogHoja1)' style='color: #000099'");} %> >Ver</a></td>
        </tr>
        <tr>
            <td>F&oacute;rmula 69 Hoja 2: </td>
            <td><input id= "hoja2" type="file" name="fotoF69Hoja2" accept="image/*" onchange="canvasimage('hoja2','canvasHoja2')"/></td>
            <div id="dialogHoja2" style="display:none" title="Formula 69">
                <img src="<%= src[4] %>"width="100%"/>
            </div>
            <td><canvas id="canvasHoja2" hidden="hidden"> </canvas></td>
            <td><a <% if (!src[4].equals("")){ out.print("onclick='abrir_dialog(dialogHoja2)' style='color: #000099'");} %> >Ver</a></td>
        </tr>
        <tr>
            <td>F&oacute;rmula 69 Hoja 3: </td>
            <td><input id= "hoja3" type="file" name="fotoF69Hoja3" accept="image/*" onchange="canvasimage('hoja3','canvasHoja3')"/></td>
            <div id="dialogHoja3" style="display:none" title="Formula 69">
                <img src="<%= src[5] %>"width="100%"/>
            </div>
            <td><canvas id="canvasHoja3" hidden="hidden"> </canvas></td>
            <td><a <% if (!src[5].equals("")){ out.print("onclick='abrir_dialog(dialogHoja3)' style='color: #000099'");} %> >Ver</a></td>
        </tr>
        <tr>
            <td>
                </br>
            </td>
        </tr>
        <tr>
            <td>Formulario N&deg;1 (A &oacute; B): </td>
            <td><input id= "f1hoja1" type="file" name="fotoF1Hoja1" accept="image/*" onchange="canvasimage('f1hoja1','canvasf1Hoja1')"/></td>
            <div id="dialogf1Hoja1" style="display:none" title="Formulario Nro. 1 Hoja 1">
                <img id="imagenf1hoja1" src="<%= src[6] %>" width="100%"/>
            </div>
            <td><canvas id="canvasf1Hoja1" hidden="hidden"> </canvas></td>
            <td><a <% if (!src[6].equals("")){ out.print("onclick='abrir_dialog(dialogf1Hoja1)' style='color: #000099'");} %> >Ver</a></td>
        </tr>
        <tr style='display: none'>
            <td>Formulario N&deg;1 (A &oacute; B) Hoja 2: </td>
            <td><input id= "f1hoja2" type="file" name="fotoF1Hoja2" accept="image/*" onchange="canvasimage('f1hoja2','canvasf1Hoja2')"/></td>
            <div id="dialogf1Hoja2" style="display:none" title="Formulario Nro. 1 Hoja2">
                <img src="<%= src[7] %>"width="100%"/>
            </div>
            <td><canvas id="canvasf1Hoja2" hidden="hidden"> </canvas></td>
            <td><a <% if (!src[7].equals("")){ out.print("onclick='abrir_dialog(dialogf1Hoja2)' style='color: #000099'");} %> >Ver</a></td>
        </tr>
    </table>
    <p align="right"> <input style="font-size: 18px" type="submit" <% if (request.getParameter("ci")==null){ out.print("value='Guardar y Continuar'");} else{out.print("value='Modificar'");} %> /> </p>
                                             
</form>