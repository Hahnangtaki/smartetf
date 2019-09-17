import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISubscriptMySuffix, defaultValue } from 'app/shared/model/subscript-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_SUBSCRIPT_LIST: 'subscript/FETCH_SUBSCRIPT_LIST',
  FETCH_SUBSCRIPT: 'subscript/FETCH_SUBSCRIPT',
  CREATE_SUBSCRIPT: 'subscript/CREATE_SUBSCRIPT',
  UPDATE_SUBSCRIPT: 'subscript/UPDATE_SUBSCRIPT',
  DELETE_SUBSCRIPT: 'subscript/DELETE_SUBSCRIPT',
  RESET: 'subscript/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISubscriptMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type SubscriptMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: SubscriptMySuffixState = initialState, action): SubscriptMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SUBSCRIPT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SUBSCRIPT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_SUBSCRIPT):
    case REQUEST(ACTION_TYPES.UPDATE_SUBSCRIPT):
    case REQUEST(ACTION_TYPES.DELETE_SUBSCRIPT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_SUBSCRIPT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SUBSCRIPT):
    case FAILURE(ACTION_TYPES.CREATE_SUBSCRIPT):
    case FAILURE(ACTION_TYPES.UPDATE_SUBSCRIPT):
    case FAILURE(ACTION_TYPES.DELETE_SUBSCRIPT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_SUBSCRIPT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_SUBSCRIPT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_SUBSCRIPT):
    case SUCCESS(ACTION_TYPES.UPDATE_SUBSCRIPT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_SUBSCRIPT):
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

const apiUrl = 'api/subscripts';

// Actions

export const getEntities: ICrudGetAllAction<ISubscriptMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_SUBSCRIPT_LIST,
  payload: axios.get<ISubscriptMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ISubscriptMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SUBSCRIPT,
    payload: axios.get<ISubscriptMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ISubscriptMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SUBSCRIPT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISubscriptMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SUBSCRIPT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISubscriptMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SUBSCRIPT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
