import React from 'react'
import styles from './Review.module.scss'
import classNames from 'classnames/bind'
import { Button } from 'antd';
import ItemReview  from '../itemReview/itemReview';
import ReactStars from "react-rating-stars-component";
const cx= classNames.bind(styles);
const Review = () => {
    const ratingChanged = (newRating) => {
        console.log(newRating);
      };
  return (
    <div className={cx('WrapReview')}>
        <div>
            <h3>ĐÁNH GIÁ (2)</h3>
            <div className={cx('reviewComment')}>
            <ReactStars
                count={5}
                onChange={ratingChanged}
                size={24}
                isHalf={true}
                emptyIcon={<i className="far fa-star"></i>}
                halfIcon={<i className="fa fa-star-half-alt"></i>}
                fullIcon={<i className="fa fa-star"></i>}
                activeColor="#ffd700"
            />
               <textarea
                className={cx('reviewCommentTxt')}
                placeholder='Nhận xét của bạn' />
                <div className={cx('reviewCommenbtn')}>
                    <Button
                    >
                        Send
                    </Button >   
                </div>
            </div>
            <ItemReview  />
        </div>
    
     
    </div>
  )
}

export default Review
