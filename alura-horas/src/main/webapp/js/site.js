$( document ).ready(function() {
	var now = new Date();
	var dias = [
        "01/01/" + now.getFullYear(),
        "08/01/" + now.getFullYear(),

        //Fevereiro
        "09/02/" + now.getFullYear(),
        "10/02/" + now.getFullYear(),

        //Março
        "01/03/" + now.getFullYear(),
        "08/03/" + now.getFullYear(),
        "23/03/" + now.getFullYear(),

        //Abril
        "19/04/" + now.getFullYear(),
        "21/04/" + now.getFullYear(),
        "22/04/" + now.getFullYear(),
        "23/04/" + now.getFullYear(),

        //Maio
        "01/05/" + now.getFullYear(),
        "28/05/" + now.getFullYear(),

        //Junho

        //Agosto
        "14/08/" + now.getFullYear(),

        //Setembro
        "07/09/" + now.getFullYear(),

        //Outubro
        "12/10/" + now.getFullYear(),
        "15/10/" + now.getFullYear(),
        "19/10/" + now.getFullYear(),
        "20/10/" + now.getFullYear(),

        //Novembro
        "02/11/" + now.getFullYear(),
        "15/11/" + now.getFullYear(),
        "19/11/" + now.getFullYear(),
        "20/11/" + now.getFullYear(),

        //Dezembro
        "25/12/" + now.getFullYear(),
    ];
	$("#data").datepicker({
		  changeYear: true,
		  changeMonth: true,
		  yearRange: "1002:2018",
		  dateFormat: 'dd/mm/yy',
		  beforeShowDay: function(dateText, inst) {
		    var datepickerDay = ('0' + dateText.getDate()).slice(-2) + '/' + ('0' + (dateText.getMonth()+1)).slice(-2) + '/' + dateText.getFullYear();
		    if(dias.indexOf(datepickerDay.trim()) > -1) {
		      return [false, "", ""];
		    }
		    return [true, "", "disponivel"];
		  }
	});
	$('#horasTableId').DataTable();
	$('#horaInicial').timepicker({
		timeFormat: 'HH:mm',
	    interval: 1,
	    minTime: '9',
	    maxTime: '6:00pm',
	    defaultTime: '9',
	    startTime: '9:00',
	    dynamic: false,
	    dropdown: true,
	    scrollbar: true
	});
	$('#saidaAlmoco').timepicker({
		timeFormat: 'HH:mm',
	    interval: 1,
	    minTime: '9',
	    maxTime: '6:00pm',
	    defaultTime: '12',
	    startTime: '9:00',
	    dynamic: false,
	    dropdown: true,
	    scrollbar: true
	});
	$('#voltaAlmoco').timepicker({
		timeFormat: 'HH:mm',
	    interval: 1,
	    minTime: '9',
	    maxTime: '6:00pm',
	    defaultTime: '13',
	    startTime: '9:00',
	    dynamic: false,
	    dropdown: true,
	    scrollbar: true
	});
	$('#horaFinal').timepicker({
		timeFormat: 'HH:mm',
	    interval: 1,
	    minTime: '9',
	    maxTime: '6:00pm',
	    defaultTime: '18',
	    startTime: '9:00',
	    dynamic: false,
	    dropdown: true,
	    scrollbar: true
	});
		
    $("#mes").change(function() {
    	var mes = $("#mes").val();
    	$.ajax({
    		url: '/alura-horas/horaLancada/listaPorMes',
    		data:{'mes':mes},
    		type: 'post',
    		datatype: 'json',
    		success: function(data){
    			var table = $('#horasTableId').DataTable();
    			table.clear();
                table.destroy();
    			$("#horasTableId").empty();
    			$("#horasTableId").append(
						'<thead>'+
							'<tr>'+
								'<th>Nome</th>'+
								'<th>Data</th>'+
								'<th>Hora Inicial</th>'+
								'<th>Saída para o almoço</th>'+
								'<th>Volta do almoço</th>'+
								'<th>Hora Final</th>'+
								'<th>Duração</th>'+
								'<th></th>'+
								'<th></th>'+
							'</tr>'+
						'</thead>'+
						'<tbody>');
    			var duracaoTotal = 0;
    			$.each(data, function (index, horaLancada) {
    				var i = data[index].horaInicial.split(':');
    				var s = data[index].saidaAlmoco.split(':');
    				var v = data[index].voltaAlmoco.split(':');
    				var f = data[index].horaFinal.split(':');
    				var inicio = parseInt(i[0])* 60+ parseInt(i[1]);
    				var saida = parseInt(s[0])* 60+ parseInt(s[1]);
    				var volta = parseInt(v[0])* 60+ parseInt(v[1]);
    				var fim = parseInt(f[0])* 60+ parseInt(f[1]);
    				var duracao = (saida - inicio)+(fim - volta);
    				var dataFormatada = new Date(data[index].data);
    				var dia = dataFormatada.getDate();
    				var mes =  dataFormatada.getMonth();
    				mes += 1;  // JavaScript months are 0-11
    				var ano = dataFormatada.getFullYear();
    				duracaoTotal += duracao;
    				minutosDuracao = ""+duracao%60;
    				horasDuracao = ""+parseInt(duracao/60);
    				alert(horasDuracao.length);
    				alert(minutosDuracao.length);
    				if(minutosDuracao.length < 2){
    					minutosDuracao = "0"+minutosDuracao;
    				}
    				if(horasDuracao.length < 2){
    					horasDuracao = "0"+horasDuracao;
    				}
    				$("#horasTableId").append(
					'<tr>'+
						'<td>'+data[index].usuario.nome+'</td>'+
						'<td>'+dia+'/'+mes+'/'+ano+'</td>'+
						'<td>'+data[index].horaInicial+'</td>'+
						'<td>'+data[index].saidaAlmoco+'</td>'+
						'<td>'+data[index].voltaAlmoco+'</td>'+
						'<td>'+data[index].horaFinal+'</td>'+
						'<td>'+horasDuracao+":"+minutosDuracao+'</td>'+
						'<td><a href="preparaEdita/'+data[index].id+'">Editar</a></td>'+
						'<td><a href="remove/'+data[index].id+'">Excluir</a></td>'+
					'</tr>');
	
                });
    			if(duracaoTotal>0){
	    			$("#horasTableId").append(
	    			'</tbody>'+
	    			'<tfoot>'+
	    				'<tr>'+
	    					'<td></td>'+
	    					'<td></td>'+
	    					'<td></td>'+
	    			
	    					'<td></td>'+
	        				'<td></td>'+
	    					'<td>Total:</td>'+
	            			'<td>'+parseInt(duracaoTotal/60)+":"+duracaoTotal%60+'</td>'+
	    					'<td></td>'+
	    					'<td></td>'+
	    				'</tr>'+
	    			'</tfoot>');
    			}else{
    				$("#horasTableId").append(
    		    			'</tbody>'+
    		    			'<tfoot>'+
    		    				'<tr>'+
    		    					'<td></td>'+
    		    					'<td></td>'+
    		    					'<td></td>'+
    		    			
    		    					'<td></td>'+
    		        				'<td></td>'+
    		    					'<td></td>'+
    		            			'<td></td>'+
    		    					'<td></td>'+
    		    					'<td></td>'+
    		    				'</tr>'+
    		    			'</tfoot>');
    			}
    			table = $('#horasTableId').DataTable();
    		}
    	});
    });
});