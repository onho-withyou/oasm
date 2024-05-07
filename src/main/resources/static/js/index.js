$(function(){
    $(".hidebtn").on("click", function(){
        toggleMenu($(this));
    });

});
function toggleMenu(btn) {

    if (btn.attr('id') === 'hidebtn') {
        var hidemenu = $("#main-tree-ul");
    } else {
        var hidemenu = btn.closest(".main-tree-li").find(".sub-tree-ul");
    }
    var img = btn.parent().find("img");

    if(hidemenu.css('display') === 'none'){
        hidemenu.show();
        img.attr("src", "../img/closebtn_icon.png");
    } else{
        hidemenu.hide();
        img.attr("src", "../img/openbtn2_icon.png");
    }
}



/*
function togglebtn(){
    var hidemenu = document.getElementById("main-tree-ul");
    var img = document.getElementById("hidebtn");
    if(hidemenu.style.display === "none"){
        hidemenu.style.display ="block";
        img.src="../img/closebtn_icon.png"
    }else{
        hidemenu.style.display = "none";
        img.src="../img/openbtn2_icon.png"
    }
}
function togglebtn1(){
    var hidemenu = document.getElementById("sub-tree-ul1");
    var img = document.getElementById("hidebtn1");
    if(hidemenu.style.display === "none"){
        hidemenu.style.display ="block";
        img.src="../img/closebtn_icon.png"
    }else{
        hidemenu.style.display = "none";
        img.src="../img/openbtn2_icon.png"
    }
}
function togglebtn2(){
    var hidemenu = document.getElementById("sub-tree-ul2");
    var img = document.getElementById("hidebtn2");
    if(hidemenu.style.display === "none"){
        hidemenu.style.display ="block";
        img.src="../img/closebtn_icon.png"
    }else{
        hidemenu.style.display = "none";
        img.src="../img/openbtn2_icon.png"
    }
}
function togglebtn3(){
    var hidemenu = document.getElementById("sub-tree-ul3");
    var img = document.getElementById("hidebtn3");
    if(hidemenu.style.display === "none"){
        hidemenu.style.display ="block";
        img.src="../img/closebtn_icon.png"
    }else{
        hidemenu.style.display = "none";
        img.src="../img/openbtn2_icon.png"
    }
}*/
