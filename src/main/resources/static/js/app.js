const slickSlide = jQuery('#slick-slide')

if (slickSlide) {
  slickSlide.slick({
    variableWidth: true,
    dots: true,
    arrows: false,
    slidesToShow: 4,
    slideToScroll: 1,
    autoplay: true,
    autoplaySpeed: 1700,
    responsive: [
      {
        breakpoint: 768,
        settings: {
          slidesToShow: 2
        }
      },
      {
        breakpoint: 576,
        settings: {
          slidesToShow: 1
        }
      }
    ]
  })
}


