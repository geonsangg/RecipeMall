$(function () {
    document.getElementById('Ldate').valueAsDate = new Date();
    $('.Sdate').click(function() {
    var today = new Date();
    var sevenDaysAgo = new Date(today.getTime() - (7 * 24 * 60 * 60 * 1000));
    var formattedDate = sevenDaysAgo.toISOString().substring(0, 10);
    $('#Fdate').val(formattedDate);
  });
    $('.Odate').click(function() {
        var today = new Date();
        var thirtyDaysAgo = new Date(today.getTime() - (30 * 24 * 60 * 60 * 1000));
        var formattedDate = thirtyDaysAgo.toISOString().substring(0, 10);
        $('#Fdate').val(formattedDate);
    });
    $('.Edate').click(function() {
    var today = new Date();
    var oneYearAgo = new Date(today.getTime() - (365 * 24 * 60 * 60 * 1000));
    var formattedDate = oneYearAgo.toISOString().substring(0, 10);
    $('#Fdate').val(formattedDate);
  });
});