import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IBankCustodyMySuffix, defaultValue } from 'app/shared/model/bank-custody-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_BANKCUSTODY_LIST: 'bankCustody/FETCH_BANKCUSTODY_LIST',
  FETCH_BANKCUSTODY: 'bankCustody/FETCH_BANKCUSTODY',
  CREATE_BANKCUSTODY: 'bankCustody/CREATE_BANKCUSTODY',
  UPDATE_BANKCUSTODY: 'bankCustody/UPDATE_BANKCUSTODY',
  DELETE_BANKCUSTODY: 'bankCustody/DELETE_BANKCUSTODY',
  RESET: 'bankCustody/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IBankCustodyMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type BankCustodyMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: BankCustodyMySuffixState = initialState, action): BankCustodyMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_BANKCUSTODY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_BANKCUSTODY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_BANKCUSTODY):
    case REQUEST(ACTION_TYPES.UPDATE_BANKCUSTODY):
    case REQUEST(ACTION_TYPES.DELETE_BANKCUSTODY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_BANKCUSTODY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_BANKCUSTODY):
    case FAILURE(ACTION_TYPES.CREATE_BANKCUSTODY):
    case FAILURE(ACTION_TYPES.UPDATE_BANKCUSTODY):
    case FAILURE(ACTION_TYPES.DELETE_BANKCUSTODY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_BANKCUSTODY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_BANKCUSTODY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_BANKCUSTODY):
    case SUCCESS(ACTION_TYPES.UPDATE_BANKCUSTODY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_BANKCUSTODY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/bank-custodies';

// Actions

export const getEntities: ICrudGetAllAction<IBankCustodyMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_BANKCUSTODY_LIST,
  payload: axios.get<IBankCustodyMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IBankCustodyMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BANKCUSTODY,
    payload: axios.get<IBankCustodyMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IBankCustodyMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_BANKCUSTODY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IBankCustodyMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_BANKCUSTODY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IBankCustodyMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_BANKCUSTODY,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
