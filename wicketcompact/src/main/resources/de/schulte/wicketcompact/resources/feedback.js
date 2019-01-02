(function ($) {
    $.fn.hideFeedback = function () {
        const feedbackPanel = $(this);
        window.setTimeout(() => {
            feedbackPanel.hide(2000);
        }, 4000);
    }
}(jQuery));