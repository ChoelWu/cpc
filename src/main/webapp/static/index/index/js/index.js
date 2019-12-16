$(function () {
    $("*[data-flash]").each(function (index, val) {
        var url = $(val).attr('data-flash');
        $(val).flash({
            swf: url,
            width: '100%',
            height: 800
        });
    });
});