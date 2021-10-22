# Beaser
[![PayPal][badge_paypal]][paypal-donations] [![CodeFactor](https://www.codefactor.io/repository/github/lukasgasp/beaser/badge?s=e6bb254c60f5f414d23bb944df6fe7da88a2e11a)](https://www.codefactor.io/repository/github/lukasgasp/beaser)
> A little java/python project for using a Beamer as Laser over sACN

<a href="#DMX-Footprint">DMX-Footprint</a> •
<a href="#Modes">Modes</a> •
<a href="#Macros">Macros</a> 

# Control:
The compiled Programm takes the universe 98 and has 5 Objects
You can control multiple Objects. For this you can change the ```amount``` integer in the Java. It defines your objects. (Each Object takes 10 adresses)
## DMX-Footprint per Fixture:

    01: Background R
    02: Background G
    03: Background B

    04: Macro                 => //TODO: Not done yet.     
    05: Macro-Speed           => //TODO: Not done yet. Footprint is 10

## DMX-Footprint per Object

    01:  Mode                 => Modes listed under #Modes

    02:  Dimmer

    03:  x-Position
    04:  y-Position

    05:  x-Size
    06:  y-Size

    07:  R
    08:  G
    09:  B

    10: Effect options        => Options listed under #Effect Options

    11: Shutter               => //TODO: Not done yet. Footprint is 10

## Modes:
    0: OFF
    1: Horizontal Line
    2: Vertical Line
    3: Rectangle
    4: Oval
    5: Poly Line
    6: Image
    7: Arc

## Effect Options:
## For Images:
    1: NGK-Logo
    2: NGK (Written)
    3: FMT

# Macros:
Comming soon

# Contact Me:

[![Mail me][gmail_logo]][gmail]

[badge_paypal]: https://user-images.githubusercontent.com/6497827/53698092-42032280-3dfe-11e9-8054-1597c62d344e.png
[paypal-donations]: https://paypal.me/dergaspar

[gmail_logo]: https://user-images.githubusercontent.com/6497827/62424751-c1b85480-b6f0-11e9-97de-096c0a980829.png
[gmail]: mailto:lukasfg2005@gmail.com?subject=Regarding%20Beaser&body=Hi
