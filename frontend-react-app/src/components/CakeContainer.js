import React from 'react';
import { connect } from 'react';
import { buyCake } from '../redux'

function CakeContainer (props) {
    return (
        <div>
            <h2>Number of cakes - {props.numberOfCakes}</h2>
            <button onClick={props.buyCake}>Buy Cake</button>
        </div>
    )
}

const mapStateToProps = state => {
    return {
        numOfCakes: state.numOfCakes
    }
}

export default CakeContainer;