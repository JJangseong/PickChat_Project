import React from 'react';
import './style.css';

const SubComponent = ({content, content2, img}) => {
    return(
        <div >
            <center><div style={style.container} className="sub2pic" /></center>
            {/* <div style={{textAlign: 'center', color:'black'}}>
                <h1 style={{margin: '15px'}}>{content}</h1>                       
            </div>
            <div style={style.img}/> */}
            {/* <ReactPlayer
                    playsInline
                    url='https://www.youtube.com/watch?v=mPRy1B4t5YA' playing
                    width={2000}
                    height={900}
                    fluid={false}
                /> */}
        </div>
    );
}

const style={
    container:{
        width:"2000px",
        height:"400px",
        margin:"0 auto",
        // paddingTop: '80px',
        backgroundColor:'#FFE3FF'
    }
}

export default SubComponent