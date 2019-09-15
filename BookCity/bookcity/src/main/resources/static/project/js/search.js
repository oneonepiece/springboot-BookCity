
var proSearch = function () {

    $('#bc-search').click(function () {

        var key = $('#bc-search-value').val();
        if (key != "" && key != null && key != " ") {
           var uri = "/search?keyword=" + key.toString() + "&page=1";
            window.location.href = uri;
        }
    });

}
