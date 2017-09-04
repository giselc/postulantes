$(function() {
    var encodeButton = document.getElementById('uploadImage');

    //HANDLE COMPRESS BUTTON
    encodeButton.addEventListener('onchange', function(e) {
        
        var source_image = document.getElementById('uploadImage');
        var quality = 30;
        source_image.src = jic.compress(source_image,quality,"jpeg").src;
    
    }, false);

});


