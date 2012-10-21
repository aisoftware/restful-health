jQuery.fn.exists = function(){return this.length>0;};

(function($) {

    $.fn.extend({

        quickTabs: function() {

            var container = this;
            var $tabs = $('.nav-list li', container);

            $tabs.click(function() {

                var $this = $(this);
                var tab_class = $this.children('a').html().toLowerCase().replace(' ', '-');

                $tabs.removeClass('active');
                $this.addClass('active');

                $('.span9').hide();
                $('.span9.' + tab_class, container).show();
                
                //primary page initiation event loop YYEEAAA need to refactor lol this is a bad pattern
                if (tab_class==='payers') {
                	renderPayer();
                };
                
                return false;

           });

           return this;
        }

    });

})(jQuery);

function getAge(dateString) {
    var today = new Date();
    var birthDate = new Date(dateString);
    var age = today.getFullYear() - birthDate.getFullYear();
    var m = today.getMonth() - birthDate.getMonth();
    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
        age--;
    }
    return age;
}