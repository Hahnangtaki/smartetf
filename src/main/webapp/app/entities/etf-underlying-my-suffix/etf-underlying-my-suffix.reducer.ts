import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEtfUnderlyingMySuffix, defaultValue } from 'app/shared/model/etf-underlying-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_ETFUNDERLYING_LIST: 'etfUnderlying/FETCH_ETFUNDERLYING_LIST',
  FETCH_ETFUNDERLYING: 'etfUnderlying/FETCH_ETFUNDERLYING',
  CREATE_ETFUNDERLYING: 'etfUnderlying/CREATE_ETFUNDERLYING',
  UPDATE_ETFUNDERLYING: 'etfUnderlying/UPDATE_ETFUNDERLYING',
  DELETE_ETFUNDERLYING: 'etfUnderlying/DELETE_ETFUNDERLYING',
  RESET: 'etfUnderlying/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEtfUnderlyingMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type EtfUnderlyingMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: EtfUnderlyingMySuffixState = initialState, action): EtfUnderlyingMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ETFUNDERLYING_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ETFUNDERLYING):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ETFUNDERLYING):
    case REQUEST(ACTION_TYPES.UPDATE_ETFUNDERLYING):
    case REQUEST(ACTION_TYPES.DELETE_ETFUNDERLYING):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ETFUNDERLYING_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ETFUNDERLYING):
    case FAILURE(ACTION_TYPES.CREATE_ETFUNDERLYING):
    case FAILURE(ACTION_TYPES.UPDATE_ETFUNDERLYING):
    case FAILURE(ACTION_TYPES.DELETE_ETFUNDERLYING):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ETFUNDERLYING_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ETFUNDERLYING):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ETFUNDERLYING):
    case SUCCESS(ACTION_TYPES.UPDATE_ETFUNDERLYING):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ETFUNDERLYING):
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

const apiUrl = 'api/etf-underlyings';

// Actions

export const getEntities: ICrudGetAllAction<IEtfUnderlyingMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ETFUNDERLYING_LIST,
  payload: axios.get<IEtfUnderlyingMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IEtfUnderlyingMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ETFUNDERLYING,
    payload: axios.get<IEtfUnderlyingMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEtfUnderlyingMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ETFUNDERLYING,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEtfUnderlyingMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ETFUNDERLYING,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEtfUnderlyingMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ETFUNDERLYING,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
