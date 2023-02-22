import {
  paymentMethodAddRequest,
  paymentMethodAddSuccess,
  paymentMethodAddFail,
  paymentMethodAddReset,
  paymentMethodListRequest,
  paymentMethodListSuccess,
  paymentMethodListFail,
  paymentMethodListReset
} from "../reducers/paymentSlice";

import { getErrorMessage } from '../service/CommonUtils';
import { getAllPaymentMethodsApi, savePaymentMethodApi } from '../service/RestApiCalls';

export const savePaymentMethodAction = (cardRequestBody) => async (dispatch) => {
  try {
    dispatch(paymentMethodAddRequest());

    //save payment
    await savePaymentMethodApi(cardRequestBody);

    dispatch(paymentMethodAddSuccess());
    dispatch(getMyPaymentMethodsAction());
  } catch (error) {
    dispatch(paymentMethodAddFail(getErrorMessage(error)));
  }
};

export const getMyPaymentMethodsAction = () => async (dispatch) => {
  try {
    dispatch(paymentMethodListRequest());

    //Get All my payment methods
    const paymentMethodsList = await getAllPaymentMethodsApi();

    dispatch(paymentMethodListSuccess(paymentMethodsList));
  } catch (error) {
    dispatch(paymentMethodListFail(getErrorMessage(error)));
  }
};
