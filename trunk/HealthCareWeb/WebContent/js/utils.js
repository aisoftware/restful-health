jQuery.fn.exists = function(){return this.length>0;};

(function($) {

    $.fn.extend({

        quickTabs: function() {

            var container = this;
            var $tabs = $('.nav-list li', container);

            $tabs.click(function() {
            	temporaryElementResets();
                var $this = $(this);
                var tab_class = $this.children('a').html().toLowerCase().replace(' ', '-');

                $tabs.removeClass('active');
                $this.addClass('active');

                $('.span11').hide();
                $('.span11.' + tab_class, container).show();
                
                //primary page initiation event loop YYEEAAA need to refactor lol this is a bad pattern
                if (tab_class==='payers') {
                	renderPayer();
                };
                
                if (tab_class==='lab-results') {
                	renderLabs();
                };
                
                if (tab_class==='vital-signs') {
                	renderVitals();
                };
                
                if (tab_class==='conditions') {
                	renderConditions();
                }
                
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
function translateDate(dateString) {
	var date = new Date(dateString.substring(0,4),dateString.substring(4,6),dateString.substring(6,8));
	
	return date;
}
function temporaryElementResets() {
	$('.nav-conditions-filter').hide();
}