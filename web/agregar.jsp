<%-- 
    Document   : agregar
    Created on : Mar 25, 2016, 11:23:31 PM
    Author     : Gisel
--%>

<%@page import="java.sql.Date"%>
<%@page import="java.util.Base64"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="Classes.*"%>
<%@page import="java.util.ArrayList"%>
<%@ include file="header.jsp" %> 
                            
    <script type="text/javascript">
        function PreviewImage() {
            var oFReader = new FileReader();
            oFReader.readAsDataURL(document.getElementById("uploadImage").files[0]);
            oFReader.onload = function (oFREvent) {
                document.getElementById("uploadPreview").src = oFREvent.target.result;
            };   
        };
        function canvasimage(input,canv) {
            var canvas= document.getElementById(canv);
            var ctx = canvas.getContext('2d');
            var img = new Image;
            var oFReader = new FileReader();
            oFReader.readAsDataURL(document.getElementById(input).files[0]);
            oFReader.onload = function (oFREvent) {
                img.src = oFREvent.target.result;
            };

            img.onload = function() {
                var MAX_WIDTH = 1024;
                var MAX_HEIGHT = 1024;
                var width = img.width;
                var height = img.height;
                if (width > height) {
                  if (width > MAX_WIDTH) {
                    height *= MAX_WIDTH / width;
                    width = MAX_WIDTH;
                  }
                } else {
                  if (height > MAX_HEIGHT) {
                    width *= MAX_HEIGHT / height;
                    height = MAX_HEIGHT;
                  }
                }
                canvas.width = width;
                canvas.height = height;
                ctx.drawImage(img, 0, 0, width, height);
            };

        }
        function agregarimagen(f,f1){
                  var r=confirm("¿Seguro que desea guardar los cambios?");
            if (r==true)
            {
                if(f.elements["ci"].value.length == 8 ){
                    var patron=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/;
                    if(f.elements["email"].value.search(patron)==0){
                        if (f.elements["telefono"].value.length==8 || f.elements["telefono"].value.length==9){
                            if(document.getElementById('f1hoja1').value != "" || f1!=window.location){
                                var input = document.createElement('input');input.type = 'hidden';input.name = 'foto2';
                                if(document.getElementById('uploadImage').value == ""){
                                    input.value = "";
                                }
                                else{
                                    var canvas= document.getElementById('canvas6');
                                    var dataURL = canvas.toDataURL();
                                    dataURL = dataURL.split(',');
                                    input.value = dataURL[1];
                                }
                                f.appendChild(input);
                                var anverso = document.createElement('input');anverso.type = 'hidden';anverso.name = 'fotoCIAnverso2';
                                if(document.getElementById('anverso').value == ""){
                                    anverso.value = "";
                                }
                                else{
                                    canvas= document.getElementById('canvasAnverso');
                                    dataURL = canvas.toDataURL();
                                    dataURL = dataURL.split(',');
                                    anverso.value = dataURL[1];
                                }
                                f.appendChild(anverso);
                                var reverso = document.createElement('input');reverso.type = 'hidden';reverso.name = 'fotoCIReverso2';
                                if(document.getElementById('reverso').value == ""){
                                    reverso.value = "";
                                }
                                else{
                                    canvas= document.getElementById('canvasReverso');
                                    dataURL = canvas.toDataURL();
                                    dataURL = dataURL.split(',');
                                    reverso.value = dataURL[1];
                                }
                                f.appendChild(reverso);
                                var hoja1 = document.createElement('input');hoja1.type = 'hidden';hoja1.name = 'fotoF69Hoja12';
                                if(document.getElementById('hoja1').value == ""){
                                    hoja1.value = "";
                                }
                                else{
                                    canvas= document.getElementById('canvasHoja1');
                                    dataURL = canvas.toDataURL();
                                    dataURL = dataURL.split(',');
                                    hoja1.value = dataURL[1];
                                }
                                f.appendChild(hoja1);
                                var hoja2 = document.createElement('input');hoja2.type = 'hidden';hoja2.name = 'fotoF69Hoja22';
                                if(document.getElementById('hoja2').value == ""){
                                    hoja2.value = "";
                                }
                                else{
                                    canvas= document.getElementById('canvasHoja2');
                                    dataURL = canvas.toDataURL();
                                    dataURL = dataURL.split(',');
                                    hoja2.value = dataURL[1];
                                }
                                f.appendChild(hoja2);
                                var hoja3 = document.createElement('input');hoja3.type = 'hidden';hoja3.name = 'fotoF69Hoja32';
                                if(document.getElementById('hoja3').value == ""){
                                    hoja3.value = "";
                                }
                                else{
                                    canvas= document.getElementById('canvasHoja3');
                                    dataURL = canvas.toDataURL();
                                    dataURL = dataURL.split(',');
                                    hoja3.value = dataURL[1];
                                }
                                f.appendChild(hoja3);
                                var f1hoja1 = document.createElement('input');f1hoja1.type = 'hidden';f1hoja1.name = 'fotoF1Hoja12';
                                if(document.getElementById('f1hoja1').value == ""){
                                    f1hoja1.value = "";
                                }
                                else{
                                    canvas= document.getElementById('canvasf1Hoja1');
                                    dataURL = canvas.toDataURL();
                                    dataURL = dataURL.split(',');
                                    f1hoja1.value = dataURL[1];
                                }
                                f.appendChild(f1hoja1);
                                var f1hoja2 = document.createElement('input');f1hoja2.type = 'hidden';f1hoja2.name = 'fotoF1Hoja22';
                                if(document.getElementById('f1hoja2').value == ""){
                                    f1hoja2.value = "";
                                }
                                else{
                                    canvas= document.getElementById('canvasf1Hoja2');
                                    dataURL = canvas.toDataURL();
                                    dataURL = dataURL.split(',');
                                    f1hoja2.value = dataURL[1];
                                }
                                f.appendChild(f1hoja2);
                                $('#loader').fadeIn();
                                f.submit();
                                return true;
                            }
                            else{
                                alert("Formulario 1 vacio.");
                                return false;
                            }
                        }
                        else{
                            alert("Telefono incorrecto.");
                            return false;
                        }
                    }
                    else{
                        alert("Email incorrecto.");
                        return false;
                    }
                }
                else{
                alert("Cédula incorrecta.");
                    return false;
            }
            }
            else{
                return false;
            }
        }
        function showOrientacion(b){
            if(b==true){
                document.getElementById('orien').style.display = '';
            }
            else{
                document.getElementById('orien').style.display = 'none';
            }
        }
        function showPaseDirecto(b,admin){
            c = ((new String(b).valueOf() == new String("13").valueOf()) || admin);
            if(document.getElementById('lmga').checked && c){
                document.getElementById('pd').style.display = '';
            }
            else{
                document.getElementById('pd').style.display = 'none';
            }
        }
        function showNotaPaseDirecto(b,admin){
            c = ((new String(b).valueOf() == new String("13").valueOf()) || admin);
            if(document.getElementById('paseDirecto').checked && c){
                document.getElementById('notaPaseDirecto').style.display = '';
            }
            else{
                document.getElementById('notaPaseDirecto').style.display = 'none';
            }
        }
        function showPsEjercito(){
            if(document.getElementById('ps').checked){
                document.getElementById('psEjer').style.display = '';
                document.getElementById('hijos').style.display = '';
            }
            else{
                document.getElementById('psEjer').style.display = 'none';
                document.getElementById('hijos').style.display = 'none';
            }
        }
    </script>
    <script src="js/jquery-1.9.1.min.js"></script>
    <script src="js/jquery-ui.js"></script>
    <script>
        function abrir_dialog(dialog) {
          $( dialog ).dialog({
              modal: true
          });
        };
    </script>
                            
                            
    <%
    String[] src= new String[8];
    src[0]= "images/silueta.jpg";
    for (int i=1; i<8; i++) {
        src[i]="";
    }
    Postulante p = null;
    boolean redirigir=false;
    if (request.getParameter("carrera")!=null){
        sesion.setAttribute("carrera", request.getParameter("carrera"));
    }
    if (request.getParameter("ci")!=null){
        ManejadorPostulanteDB mp = new ManejadorPostulanteDB();
        p = mp.getPostulante(Integer.valueOf(request.getParameter("ci")),Integer.valueOf(sesion.getAttribute("usuarioID").toString()),ManejadorPostulanteDB.getAnioPostula());
        if (p!=null){
            sesion.setAttribute("carrera", p.getCarrera().getCodigo());
            Blob b= p.getFoto();
            if (b!=null && (int)b.length()!= 0){
                byte[] by=b.getBytes(1,(int)b.length());
                String imgDataBase64=new String(Base64.getEncoder().encode(by));
                src[0] = "data:image/jpg;base64,"+imgDataBase64;

            }
            b= p.getFotoCIAnverso();
            if (b!=null && (int)b.length()!= 0){
                byte[] by=b.getBytes(1,(int)b.length());
                String imgDataBase64=new String(Base64.getEncoder().encode(by));
                src[1] = "data:image/jpg;base64,"+imgDataBase64;
            }
            b= p.getFotoCIReverso();
            if (b!=null && (int)b.length()!= 0){
                byte[] by=b.getBytes(1,(int)b.length());
                String imgDataBase64=new String(Base64.getEncoder().encode(by));
                src[2] = "data:image/jpg;base64,"+imgDataBase64;
            }
            b= p.getFotoF69Hoja1();
            if (b!=null && (int)b.length()!= 0){
                byte[] by=b.getBytes(1,(int)b.length());
                String imgDataBase64=new String(Base64.getEncoder().encode(by));
                src[3] = "data:image/jpg;base64,"+imgDataBase64;

            }
            b= p.getFotoF69Hoja2();
            if (b!=null && (int)b.length()!= 0){
                byte[] by=b.getBytes(1,(int)b.length());
                String imgDataBase64=new String(Base64.getEncoder().encode(by));
                src[4] = "data:image/jpg;base64,"+imgDataBase64;

            }
            b= p.getFotoF69Hoja3();
            if (b!=null && (int)b.length()!= 0){
                byte[] by=b.getBytes(1,(int)b.length());
                String imgDataBase64=new String(Base64.getEncoder().encode(by));
                src[5] = "data:image/jpg;base64,"+imgDataBase64;

            }
            b= p.getFotoF1Hoja1();
            if (b!=null && (int)b.length()!= 0){
                byte[] by=b.getBytes(1,(int)b.length());
                String imgDataBase64=new String(Base64.getEncoder().encode(by));
                src[6] = "data:image/jpg;base64,"+imgDataBase64;

            }
            b= p.getFotoF1Hoja2();
            if (b!=null && (int)b.length()!= 0){
                byte[] by=b.getBytes(1,(int)b.length());
                String imgDataBase64=new String(Base64.getEncoder().encode(by));
                src[7] = "data:image/jpg;base64,"+imgDataBase64;

            }
        }
        else{
            redirigir=true;
            response.sendRedirect("/listar.jsp");
        }
   }
   if(!redirigir){ 
   %>
                                   
   <p align="center"><label for="uploadImage" ><img src="<%= src[0] %>" id="uploadPreview" style="width: 20%" onclick=""/></label></p>

    <script>
         $(document).ready(function() {
                 $("#content div").hide();
                 $("#tabs li:first").attr("id","current");
                 $("#content div:first").fadeIn();
                 $("#loader").fadeOut();
             $('#tabs a').click(function(e) {
                 document.getElementById("mensaje").innerHTML="";
                 e.preventDefault();
                 $("#content div").hide();
                 $("#tabs li").attr("id","");
                 $(this).parent().attr("id","current");
                 $('#' + $(this).attr('title')).fadeIn();
             });
         })();
     </script>
     <ul id="tabs">
        <li><a href="#" title="Datos-Personales">Datos Personales</a></li>
         <li <% if (request.getParameter("ci")==null){ out.print("hidden='hidden'");} %>><a href="#" title="Datos-Patronimicos">Datos Patron&iacute;micos</a></li>
     </ul>
    <div id="content">
         <div id="Datos-Personales"><%@include file="datosBasicos.jsp" %></div>
         <div id="Datos-Patronimicos"><%@include file="datosPatronimicos.jsp" %></div>
     </div>
     <a href="listar.jsp">Atr&aacute;s</a></p>
                                            
       <%
    }
       %>                         
<%@ include file="footer.jsp" %>        