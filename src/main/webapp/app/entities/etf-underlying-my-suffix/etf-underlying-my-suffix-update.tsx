import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IEtfProductMySuffix } from 'app/shared/model/etf-product-my-suffix.model';
import { getEntities as getEtfProducts } from 'app/entities/etf-product-my-suffix/etf-product-my-suffix.reducer';
import { getEntity, updateEntity, createEntity, reset } from './etf-underlying-my-suffix.reducer';
import { IEtfUnderlyingMySuffix } from 'app/shared/model/etf-underlying-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEtfUnderlyingMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEtfUnderlyingMySuffixUpdateState {
  isNew: boolean;
  etfProductId: string;
}

export class EtfUnderlyingMySuffixUpdate extends React.Component<IEtfUnderlyingMySuffixUpdateProps, IEtfUnderlyingMySuffixUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      etfProductId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getEtfProducts();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { etfUnderlyingEntity } = this.props;
      const entity = {
        ...etfUnderlyingEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/etf-underlying-my-suffix');
  };

  render() {
    const { etfUnderlyingEntity, etfProducts, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="smartEtfApp.etfUnderlying.home.createOrEditLabel">Create or edit a EtfUnderlying</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : etfUnderlyingEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="etf-underlying-my-suffix-id">ID</Label>
                    <AvInput id="etf-underlying-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="effectiveDateLabel" for="etf-underlying-my-suffix-effectiveDate">
                    Effective Date
                  </Label>
                  <AvField id="etf-underlying-my-suffix-effectiveDate" type="date" className="form-control" name="effectiveDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="activeLabel" check>
                    <AvInput id="etf-underlying-my-suffix-active" type="checkbox" className="form-control" name="active" />
                    Active
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label for="etf-underlying-my-suffix-etfProduct">Etf Product</Label>
                  <AvInput id="etf-underlying-my-suffix-etfProduct" type="select" className="form-control" name="etfProductId">
                    <option value="" key="0" />
                    {etfProducts
                      ? etfProducts.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/etf-underlying-my-suffix" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  etfProducts: storeState.etfProduct.entities,
  etfUnderlyingEntity: storeState.etfUnderlying.entity,
  loading: storeState.etfUnderlying.loading,
  updating: storeState.etfUnderlying.updating,
  updateSuccess: storeState.etfUnderlying.updateSuccess
});

const mapDispatchToProps = {
  getEtfProducts,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EtfUnderlyingMySuffixUpdate);
