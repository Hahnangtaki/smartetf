import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IRedemptionMySuffix, defaultValue } from 'app/shared/model/redemption-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_REDEMPTION_LIST: 'redemption/FETCH_REDEMPTION_LIST',
  FETCH_REDEMPTION: 'redemption/FETCH_REDEMPTION',
  CREATE_REDEMPTION: 'redemption/CREATE_REDEMPTION',
  UPDATE_REDEMPTION: 'redemption/UPDATE_REDEMPTION',
  DELETE_REDEMPTION: 'redemption/DELETE_REDEMPTION',
  RESET: 'redemption/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IRedemptionMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type RedemptionMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: RedemptionMySuffixState = initialState, action): RedemptionMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_REDEMPTION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_REDEMPTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_REDEMPTION):
    case REQUEST(ACTION_TYPES.UPDATE_REDEMPTION):
    case REQUEST(ACTION_TYPES.DELETE_REDEMPTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_REDEMPTION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_REDEMPTION):
    case FAILURE(ACTION_TYPES.CREATE_REDEMPTION):
    case FAILURE(ACTION_TYPES.UPDATE_REDEMPTION):
    case FAILURE(ACTION_TYPES.DELETE_REDEMPTION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_REDEMPTION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_REDEMPTION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_REDEMPTION):
    case SUCCESS(ACTION_TYPES.UPDATE_REDEMPTION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_REDEMPTION):
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

const apiUrl = 'api/redemptions';

// Actions

export const getEntities: ICrudGetAllAction<IRedemptionMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_REDEMPTION_LIST,
  payload: axios.get<IRedemptionMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IRedemptionMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_REDEMPTION,
    payload: axios.get<IRedemptionMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IRedemptionMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_REDEMPTION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IRedemptionMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_REDEMPTION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IRedemptionMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_REDEMPTION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
