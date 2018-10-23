function ajaxs(address, type, async, data, dataType, stat) {
    $.ajax({
        url: address,
        type: type,
        async: async,
        data: data,
        dataType: dataType,
        success: function (result) {
            alert(result);
            stat.next(result);
        },
        err: function (e) {
            alert(e);
            stat.next(e);
        }
    })
}