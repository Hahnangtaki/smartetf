import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPortofolioMySuffix, defaultValue } from 'app/shared/model/portofolio-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_PORTOFOLIO_LIST: 'portofolio/FETCH_PORTOFOLIO_LIST',
  FETCH_PORTOFOLIO: 'portofolio/FETCH_PORTOFOLIO',
  CREATE_PORTOFOLIO: 'portofolio/CREATE_PORTOFOLIO',
  UPDATE_PORTOFOLIO: 'portofolio/UPDATE_PORTOFOLIO',
  DELETE_PORTOFOLIO: 'portofolio/DELETE_PORTOFOLIO',
  RESET: 'portofolio/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPortofolioMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type PortofolioMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: PortofolioMySuffixState = initialState, action): PortofolioMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PORTOFOLIO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PORTOFOLIO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PORTOFOLIO):
    case REQUEST(ACTION_TYPES.UPDATE_PORTOFOLIO):
    case REQUEST(ACTION_TYPES.DELETE_PORTOFOLIO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PORTOFOLIO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PORTOFOLIO):
    case FAILURE(ACTION_TYPES.CREATE_PORTOFOLIO):
    case FAILURE(ACTION_TYPES.UPDATE_PORTOFOLIO):
    case FAILURE(ACTION_TYPES.DELETE_PORTOFOLIO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PORTOFOLIO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PORTOFOLIO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PORTOFOLIO):
    case SUCCESS(ACTION_TYPES.UPDATE_PORTOFOLIO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PORTOFOLIO):
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

const apiUrl = 'api/portofolios';

// Actions

export const getEntities: ICrudGetAllAction<IPortofolioMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PORTOFOLIO_LIST,
  payload: axios.get<IPortofolioMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPortofolioMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PORTOFOLIO,
    payload: axios.get<IPortofolioMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPortofolioMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PORTOFOLIO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPortofolioMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PORTOFOLIO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPortofolioMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PORTOFOLIO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
