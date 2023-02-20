import {
  addressAddRequest,
  addressAddSuccess,
  addressAddFail,
  addressAddReset,
  addressListRequest,
  addressListSuccess,
  addressListFail,
  addressListReset,
  addressDeleteRequest,
  addressDeleteSuccess,
  addressDeleteFail,
  addressDeleteReset
} from '../reducers/addressSlice';
import { getErrorMessage } from '../service/CommonUtils';
import { getAllAddressesApi, saveAddressApi, deleteAddressApi } from '../service/RestApiCalls';

export const saveAddressAction = (addressRequestBody) => async (dispatch) => {
  try {
    dispatch(addressAddRequest());    
    //save address
    await saveAddressApi(addressRequestBody);

    dispatch(addressAddSuccess);
    dispatch(getMyAddresesAction());
  } catch (error) {
    dispatch(addressAddFail(getErrorMessage(error)));
  }
};

export const getMyAddresesAction = () => async (dispatch) => {
  try {
    dispatch(addressListRequest);

    //Get All my addresses
    const myAddressData = await getAllAddressesApi();

    dispatch(addressListSuccess(myAddressData));
  } catch (error) {
    dispatch(addressListFail(getErrorMessage(error)));
  }
};

export const deleteAddressAction = (addressId) => async (dispatch) => {
  try {
    dispatch(addressDeleteRequest);

    //save address
    await deleteAddressApi(addressId);

    dispatch(addressDeleteSuccess);
    dispatch(getMyAddresesAction());
  } catch (error) {
    dispatch(addressDeleteFail(getErrorMessage(error)));
  }
};
