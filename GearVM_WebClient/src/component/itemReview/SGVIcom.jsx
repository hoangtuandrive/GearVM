const SVGIcon = (props) =>
  <svg className={props.className} pointerEvents="none">
    <use xlinkHref={props.href} />
  </svg>;