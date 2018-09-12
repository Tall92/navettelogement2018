$(document).ready(function (e) {

    $("select#site").change(function (e) {
        
        var site = encodeURIComponent($("select#site").val());

        $.ajax({
            url: "responsable",
            type: 'POST',
            data: 'action=meschambres&site=' + site,
            dataType: 'text',
            success: function (data) {
                $("select#chambre").html(data);
            }
        });
    });
    
});