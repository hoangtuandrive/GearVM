import React from 'react'
import PropTypes from 'prop-types'
import styles from '../../sass/Grid.modulo.scss'
import classNames from 'classnames/bind'
const cx= classNames.bind(styles)
const Grid = props => {

    const style = {
        gap: props.gap ? `${props.gap}px` : '0'
    }

    const col = props.col ? `grid-col-${props.col}` : ''
    const mdCol = props.mdCol ? `grid-col-md-${props.mdCol}` : ''
    const smCol = props.smCol ? `grid-col-sm-${props.smCol}` : ''

    return (
        <div className={cx(`grid ${col} ${mdCol} ${smCol}`)} style={style}>
            {props.children}
        </div>      
    )
}

Grid.propTypes = {
    col: PropTypes.number.isRequired,
    mdCol: PropTypes.number,
    smCol: PropTypes.number,
    gap: PropTypes.number
}

export default Grid
