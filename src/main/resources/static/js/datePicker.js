$(function(){
    $(".datePicker").datepicker({
        dateFormat: "yy-mm-dd",
        showButtonPanel: "true",
        yearRange: "c-20:c",
        changeYear: true,
        changeMonth: true,
        showMonthAfterYear: true,
        dayNamesMin: ['일','월','화','수','목','금','토'],
        monthNameShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        closeText: '닫기',
        currentText: '오늘',

    });
});