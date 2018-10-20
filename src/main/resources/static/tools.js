function ajaxs(address, type, async, data, dataType) {
    $.ajax({
        url: address,
        type: type,
        async: async,
        data: data,
        dataType: dataType,
        success: function (result) {
            alert(result);
        },
        err: function (e) {
            alert(e);
        }
    })
}