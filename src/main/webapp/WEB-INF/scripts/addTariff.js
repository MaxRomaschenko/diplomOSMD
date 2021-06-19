$('.spoiler-title').click(function(){
    var content = $('div.spoiler-content.template').clone();
    content.removeClass('template');
    content.appendTo('div#spoiler-block');
});