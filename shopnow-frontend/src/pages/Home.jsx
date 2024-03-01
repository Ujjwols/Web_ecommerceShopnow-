import React, { useState } from "react";

import BaseComponent from "../components/BaseComponent";
import CarouselComponent from "../components/CarouselComponent";

import Rolex from "../images/brands/Rolex.png";
import nike from "../images/brands/nike.png";
import asus from "../images/brands/asus.jpg";
import nin from "../images/brands/nin.png";
import game from "../images/brands/game.png";
import lego from "../images/brands/lego.png";
import puma from "../images/brands/puma.jpg";
import fan from "../images/brands/fan.png";

import laptop from "../images/carousel/laptop.JPG";
import galaxy from "../images/carousel/galaxy.png";
import techno from "../images/carousel/techno.JPG";
import iphone from "../images/carousel/iphone.JPG";
import oneplus from "../images/carousel/oneplus.JPG";
import redmi from "../images/carousel/redmi.jpg";

function Home() {
  console.log("home.js rendered");
  const brandsImages = [
    { id: 1, image: Rolex, text: "Rolex logo" },
    { id: 2, image: asus, text: "asus logo" },
    { id: 3, image: nin, text: "nin logo" },
    { id: 4, image: game, text: "game logo" },
    { id: 5, image: lego, text: "lego logo" },
    { id: 6, image: nike, text: "nike logo" },
    { id: 7, image: puma, text: "puma logo" },
    { id: 8, image: fan, text: "fan " },
  ];

  const carouselitems1 = [
    {
      src: laptop,
      altText: "laptop",
      caption: "",
      key: 1,
    },
    {
      src: galaxy,
      altText: "galaxy",
      caption: "",
      key: 2,
    },
    {
      src: techno,
      altText: "techno",
      caption: "",
      key: 3,
    },
  ];

  const carouselitems2 = [
    {
      src: iphone,
      altText: "iphone",
      caption: "",
      key: 1,
    },
    {
      src: oneplus,
      altText: "oneplus",
      caption: "",
      key: 2,
    },
    {
      src: redmi,
      altText: "redmi",
      caption: "",
      key: 3,
    },
  ];

  const brands = brandsImages.map((brand) => {
    return (
      <div
        className="col-md-3 d-flex justify-content-center align-items-center mt-5"
        key={brand.id}
      >
        <img
          src={brand.image}
          alt={brand.text}
          className="img-fluid"
          height="230px"
          width="230px"
        />
      </div>
    );
  });
  return (
    <BaseComponent>
      <div className="Home" style={{minHeight:"500px"}}>
      <div>
        <h2 className="text-center fw-bold text-primary mt-3" style={{color: "black"}}>WELCOME TO SHOP-NOW</h2>
      </div>
        <div className="row" style={{marginLeft:"0px",marginRight:"0px"}}>
          <div className="col-md-6">
            <CarouselComponent items={carouselitems1} />
          </div>
          <div className="col-md-6">
            <CarouselComponent items={carouselitems2} />
          </div>
        </div>
        <div>
          <h2 className="text-center fw-bold">FEATURED BRANDS</h2>
        </div>
        <div className="row" style={{marginLeft:"0px",marginRight:"0px"}}>{brands}</div>
      </div>
    </BaseComponent>
  );
}
export default Home;
