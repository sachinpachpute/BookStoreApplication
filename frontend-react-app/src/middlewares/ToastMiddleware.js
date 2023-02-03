import {
  listProductsRequest,
  listProductsSuccess,
  listProductsFail
} from "../reducers/productSlice";
import { toast } from 'react-toastify';

const ToastMiddleware = () => next => action => {
    switch(action.type) {
        case listProductsRequest.type:
            toast.success('listProductsRequest successfully');
            break;
        case listProductsSuccess.type:
            toast.success('listProductsSuccess successfully');
            break;
        case listProductsFail.type:
            toast.success('Error listProductsFail');
            break;
        default:
            break;
    }
    return next(action);
}

export default ToastMiddleware;