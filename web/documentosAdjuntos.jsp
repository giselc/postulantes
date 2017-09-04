<form method="post"  id="formulario" onsubmit="return agregarimagen1(this);" <% if (request.getParameter("id")==null){ out.print("action='alerta(Debe crear un postulante previamente)'");} else{out.print("action='modificarDocAdjuntos'");} %> enctype="multipart/form-data">
    <table>
        <tr>
            <td>
                <h3>Documentos Adjuntos:</h3>
            </td>
        </tr>
        <tr>
            <td>C.I.: </td>
            <td><input id= "input" type="file" name="fotoCI" accept="image/*" onchange="canvasimage('input','canvas')"/></td>
            <div id="dialog" style="display:none;" title="Cedula de Identidad">
                <img src="<%= src[1] %>" width="100%"/>
            </div>
            <td><canvas id="canvas" hidden="hidden"> </canvas></td>
            <td><a <% if (!src[1].equals("")){ out.print("onclick='abrir_dialog(dialog)' style='color: #000099'");} %>>Ver</a></td>
        </tr>
        <tr>
            <td>Credencial Civica: </td>
            <td><input id= "input2" type="file" name="fotoCC"  accept="image/*" onchange="canvasimage('input2','canvas2')"/></td>
            <div id="dialog2" style="display:none" title="Credencial Civica">
                <img src="<%= src[2] %>" width="100%"/>
            </div>
            <td><canvas id="canvas2" hidden="hidden"> </canvas></td>
            <td><a <% if (!src[2].equals("")){ out.print("onclick='abrir_dialog(dialog2)' style='color: #000099'");} %>>Ver</a></td>
        </tr>
        <tr>
            <td>&Uacute;ltima constancia de voto: </td>
            <td><input id= "input3" type="file" name="fotoCV" accept="image/*" onchange="canvasimage('input3','canvas3')"/></td>
            <div id="dialog3" style="display:none" title="Constancia de voto">
                <img src="<%= src[3] %>"width="100%"/>
            </div>
            <td><canvas id="canvas3" hidden="hidden"> </canvas></td>
            <td><a <% if (!src[3].equals("")){ out.print("onclick='abrir_dialog(dialog3)' style='color: #000099'");} %>>Ver</a></td>
        </tr>
        <tr>
            <td>Certificado de buena conducta: </td>
            <td><input id= "input4" type="file" name="fotoBC" accept="image/*" onchange="canvasimage('input4','canvas4')"/></td>
            <div id="dialog4" style="display:none" title="Cert. buena conducta">
                <img src="<%= src[4] %>" width="100%"/>
            </div>
            <td><canvas id="canvas4" hidden="hidden"> </canvas></td>
            <td><a <% if (!src[4].equals("")){ out.print("onclick='abrir_dialog(dialog4)' style='color: #000099'");} %>>Ver</a></td>
        </tr>
        <tr>
            <td>F&oacute;rmula 69: </td>
            <td><input id= "input5" type="file" name="fotoF69" accept="image/*" onchange="canvasimage('input5','canvas5')"/></td>
            <div id="dialog5" style="display:none" title="Formula 69">
                <img src="<%= src[5] %>"width="100%"/>
            </div>
            <td><canvas id="canvas5" hidden="hidden"> </canvas></td>
            <td><a <% if (!src[5].equals("")){ out.print("onclick='abrir_dialog(dialog5)' style='color: #000099'");} %>>Ver</a></td>
        </tr>
        <tr>
            <td><input name= "id" hidden="hidden" type="text" value="<%= request.getParameter("id") %>"/></td>
        </tr>
    </table>
    <p align="right"> <input style="font-size: 18px" type="submit" value='Modificar' > 
</form>