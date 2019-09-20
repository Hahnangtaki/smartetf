import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IMiMySuffix, defaultValue } from 'app/shared/model/mi-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_MI_LIST: 'mi/FETCH_MI_LIST',
  FETCH_MI: 'mi/FETCH_MI',
  CREATE_MI: 'mi/CREATE_MI',
  UPDATE_MI: 'mi/UPDATE_MI',
  DELETE_MI: 'mi/DELETE_MI',
  RESET: 'mi/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IMiMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type MiMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: MiMySuffixState = initialState, action): MiMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_MI_LIST):
    case REQUEST(ACTION_TYPES.FETCH_MI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_MI):
    case REQUEST(ACTION_TYPES.UPDATE_MI):
    case REQUEST(ACTION_TYPES.DELETE_MI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_MI_LIST):
    case FAILURE(ACTION_TYPES.FETCH_MI):
    case FAILURE(ACTION_TYPES.CREATE_MI):
    case FAILURE(ACTION_TYPES.UPDATE_MI):
    case FAILURE(ACTION_TYPES.DELETE_MI):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_MI_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_MI):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_MI):
    case SUCCESS(ACTION_TYPES.UPDATE_MI):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_MI):
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

const apiUrl = 'api/mis';

// Actions

export const getEntities: ICrudGetAllAction<IMiMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_MI_LIST,
  payload: axios.get<IMiMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IMiMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_MI,
    payload: axios.get<IMiMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IMiMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_MI,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IMiMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_MI,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IMiMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_MI,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
