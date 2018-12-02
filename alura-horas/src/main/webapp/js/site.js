$( document ).ready(function() {
    $("#mes").change(function() {
    	$.ajax({
    		url: '${buscarPontoByMesUrl}',
    		data:{'mes':mes},
    		type: 'post',
    		datatype: 'json',
    		success: function(data){
    			if(data['status'] == 'sucesso'){
    				
    			}else{
    				alert('Sistema indisponível, favor tentar mais tarde.')
    			}
    		}
    	});
    });
});